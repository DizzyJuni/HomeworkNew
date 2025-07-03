package Lesson_14.BD.repository;

import Lesson_14.BD.model.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FacultyRepository {

    public void createTableFacultyIfNotExists(final Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE if exists faculty CASCADE");
        statement.execute("""
                \tCREATE TABLE IF NOT exists faculty(
                \tid bigserial PRIMARY KEY,
                \tname text NOT NULL\s
                );""");
        statement.execute("create index if not exists faculty_id_idf on faculty (id);");
    }

    public void insertFaculty(Connection connection, Collection<Faculty> faculties) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO faculty (name) values " + toInsertValueFaculty(faculties) + ";");
    }

    public void updateFaculty(Connection connection, Faculty faculty) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE faculty SET name = ? WHERE id = ?;"
        );
        preparedStatement.setString(1, faculty.getName());
        preparedStatement.setLong(2, faculty.getId());
        preparedStatement.executeUpdate();
    }

    public int deleteByName(Connection connection, String faculty) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM faculty WHERE name = ?;")) {
            preparedStatement.setString(1, faculty.trim());
            return preparedStatement.executeUpdate();
        }
    }

    public Faculty findByName(Connection connection, String name) throws SQLException {
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
        }
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
}
