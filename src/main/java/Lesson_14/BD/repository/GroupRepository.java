package Lesson_14.BD.repository;

import Lesson_14.BD.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GroupRepository {

    public void createTableGroupIfNotExists(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE if exists \"group\" CASCADE");
        statement.execute("""
                CREATE TABLE IF NOT exists "group"(
                \tid bigserial PRIMARY KEY,
                \tname text NOT NULL,
                \tfaculty_id bigint references faculty (id)\s
                );""");
        statement.execute("create index if not exists group_name_idx on \"group\" (name);");
//        statement.execute("ALTER TABLE \"GROUP\" " +
//                "add constraint fk_group_faculty " +
//                "foreign key (faculty_id) " +
//                "references faculty (id);");
    }

    public void insert(Connection connection, Collection<Group> groups) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO \"group\" (name, faculty_id) values " + toInsertValueGroup(groups) + ";");
    }

    public void update(Connection connection, Group group) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE \"group\" SET name = ? WHERE id = ?;"
        );
        preparedStatement.setString(1, group.getName());
        preparedStatement.setLong(2, group.getId());
        preparedStatement.executeUpdate();
    }

    public int delete(Connection connection, String group) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM \"group\" WHERE name = ?;"
        );
        preparedStatement.setString(1, group);
        return preparedStatement.executeUpdate();
    }

    public Group findGroupByName(Connection connection, String name) throws SQLException {
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
        }
    }

    private String toInsertValueGroup(Collection<Group> groups) {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = " ";
        for (Group g : groups) {
            stringBuilder.append(delimiter)
                    .append("(")
                    .append("'").append(g.getName()).append("'").append(",")
                    .append(g.getFacultyId())
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
