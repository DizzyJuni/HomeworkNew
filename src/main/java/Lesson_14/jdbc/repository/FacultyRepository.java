package Lesson_14.jdbc.repository;

import Lesson_14.jdbc.configuration.ConnectionManager;
import Lesson_14.jdbc.model.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FacultyRepository {
    private final ConnectionManager connectionManager;

    public FacultyRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createTableFacultyIfNotExists() {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE if exists faculty CASCADE");
            statement.execute("""
                    \tCREATE TABLE IF NOT exists faculty(
                    \tid bigserial PRIMARY KEY,
                    \tname text NOT NULL\s
                    );""");
            statement.execute("create index if not exists faculty_id_idf on faculty (id);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Faculty findByName(String name) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM faculty where name = ?");
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Faculty> faculties = toFacultyList(rs);
                if (faculties.isEmpty()) {
                    throw new RuntimeException("Faculty not found by name: " + name);
                }
                if (faculties.size() > 1) {
                    throw new RuntimeException("Found multiple faculties by name: " + name);
                }
                return faculties.getFirst();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insertFaculty(Collection<Faculty> faculties) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO faculty (name) values " + toInsertValueFaculty(faculties) + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFaculty(Faculty faculty) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    String.format(
                            "UPDATE faculty set name = %s",
                            faculty.getName()
                    )
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Faculty> toFacultyList(ResultSet rs) throws SQLException {
        List<Faculty> result = new ArrayList<>();
        while (rs.next()) {
            result.add(toFaculty(rs));
        }
        return result;
    }

    private Faculty toFaculty(ResultSet rs) throws SQLException {
        return new Faculty(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

    private String toInsertValueFaculty(Collection<Faculty> faculties) {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = " ";
        for (Faculty f : faculties) {
            stringBuilder.append(delimiter)
                    .append("(")
                    .append("'").append(f.getName()).append("'")
                    .append(")");
            delimiter = ",";
        }
        return stringBuilder.toString();
    }
}
