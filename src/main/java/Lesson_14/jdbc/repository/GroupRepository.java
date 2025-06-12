package Lesson_14.jdbc.repository;

import Lesson_14.jdbc.configuration.ConnectionManager;
import Lesson_14.jdbc.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GroupRepository {
    private ConnectionManager connectionManager;

    public GroupRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createTableGroupIfNotExists() {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE if exists \"group\" CASCADE");
            statement.execute("""
                    CREATE TABLE IF NOT exists "group"(
                    \tid bigserial PRIMARY KEY,
                    \tname text NOT NULL,
                    \tfaculty_id bigint references faculty (id)\s
                    );""");
            statement.execute("create index if not exists group_name_idx on \"group\" (name);");
            //statement.execute("ALTER TABLE \"GROUP\" " +
            //      "add constraint fk_group_faculty " +
            //    "foreign key (faculty_id) " +
            //  "references faculty (id);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertGroup(Collection<Group> groups) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO \"group\" (name, faculty_id) values " + toInsertValueGroup(groups) + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGroup(Group group) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    String.format("UPDATE \"group\" set name = %s, faculty_id = %s",
                            group.getName(),
                            group.getFaculty_id()
                    )
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Group findGroupByName(String name) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"group\" where name = ?");
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Group> groups = toGroupList(rs);
                if (groups.isEmpty()) {
                    throw new RuntimeException("Group not found by name: " + name);
                }
                if (groups.size() > 1) {
                    throw new RuntimeException("Found multiple groups by name: " + name);
                }
                return groups.getFirst();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String toInsertValueGroup(Collection<Group> groups) {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = " ";
        for (Group g : groups) {
            stringBuilder.append(delimiter)
                    .append("(")
                    .append("'").append(g.getName()).append("'").append(",")
                    .append(g.getFaculty_id())
                    .append(")");
            delimiter = ",";
        }
        return stringBuilder.toString();
    }

    private List<Group> toGroupList(ResultSet rs) throws SQLException {
        List<Group> result = new ArrayList<>();
        while (rs.next()) {
            result.add(toGroup(rs));
        }
        return result;
    }

    private Group toGroup(ResultSet rs) throws SQLException {
        return new Group(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("faculty_id")
        );
    }
}
