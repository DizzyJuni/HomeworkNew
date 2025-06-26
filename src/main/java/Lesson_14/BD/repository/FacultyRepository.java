package Lesson_14.BD.repository;

import Lesson_14.BD.model.Faculty;

import java.sql.*;
import java.util.Collection;

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
    }

    public void deleteByName(Connection connection, Faculty faculty) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM faculty WHERE name = ?;");
        preparedStatement.setString(1, faculty.getName());
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
