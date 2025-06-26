package Lesson_14.BD.repository;

import Lesson_14.BD.model.Graduate;

import java.sql.*;

public class GraduateRepository {

    public void createTableIfNotExists(final Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE if exists graduate CASCADE");
        statement.execute("""
                \tCREATE TABLE IF NOT exists graduate(
                \tid bigserial PRIMARY KEY,
                \tname text NOT NULL,
                \tdiploma_number text NOT NULL,
                \tgraduation_year date,
                \tfaculty_name text\s
                );""");
    }

    public void insert(final Connection connection, Graduate graduate) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO graduate (name, diploma_number, graduation_year, faculty_name) VALUES (?,?,?,?);");
        preparedStatement.setString(1, graduate.getName());
        preparedStatement.setString(2, graduate.getDiplomatNumber());
        preparedStatement.setDate(3, Date.valueOf(graduate.getGraduationYear()));
        preparedStatement.setString(4, String.valueOf(graduate.getFaculty()));
        preparedStatement.executeUpdate();
    }

    public void update(Connection connection, Graduate graduate) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
                String.format(
                        "UPDATE graduate set name = %s, diplomaNumber = %s," +
                                "graduationYear = %tF, faculty_name = %s ;",
                        graduate.getName(),
                        graduate.getDiplomatNumber(),
                        graduate.getGraduationYear(),
                        graduate.getFaculty()
                )
        );
    }

    public void deleteById(final Connection connection, Graduate graduate) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM graduate WHERE id = ?;");
        preparedStatement.setLong(1, graduate.getId());
        preparedStatement.executeUpdate();
    }

    public void findAll(final Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM graduate;");
    }

    public void findById(final Connection connection, Graduate graduate) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT FROM graduate WHERE id = ?;");
        preparedStatement.setLong(1, graduate.getId());
        preparedStatement.executeUpdate();
    }
}
