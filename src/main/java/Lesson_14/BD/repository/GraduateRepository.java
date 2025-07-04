package Lesson_14.BD.repository;

import Lesson_14.BD.model.Faculty;
import Lesson_14.BD.model.Graduate;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        preparedStatement.setString(4, graduate.getFaculty() != null ? graduate.getFaculty().getName() : null);
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
                        graduate.getFaculty().getName()
                )
        );
    }

    public int deleteById(final Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM graduate WHERE id = ?;");
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    }

    public List<Graduate> findAll(final Connection connection) throws SQLException {
        List<Graduate> graduateList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM graduate;");
        while (rs.next()) {
            graduateList.add(toGraduate(rs));
        }
        return graduateList;
    }

    public Graduate findById(final Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, name, diploma_number, graduation_year, faculty_name FROM graduate WHERE id = ?;");
        preparedStatement.setLong(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                Graduate graduate = new Graduate();
                graduate.setId(resultSet.getLong("id"));
                graduate.setName(resultSet.getString("name"));
                graduate.setDiplomatNumber(resultSet.getString("diploma_number"));
                graduate.setGraduationYear(resultSet.getObject("graduation_year", LocalDate.class));
                Faculty faculty = new Faculty(resultSet.getString("faculty_name"));
                graduate.setFaculty(faculty);
                return graduate;
            }
        }
        return null;
    }

    private List<Graduate> toGraduateList(ResultSet rs) throws SQLException {
        List<Graduate> graduateList = new ArrayList<>();
        while (rs.next()) {
            graduateList.add(toGraduate(rs));
        }
        return graduateList;
    }

    private Graduate toGraduate(ResultSet resultSet) throws SQLException {
        String facultyName = resultSet.getString("faculty_name");
        Faculty faculty = facultyName != null ? new Faculty(facultyName) : null;
        return new Graduate(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("diploma_number"),
                resultSet.getObject("graduation_year", LocalDate.class),
                faculty);
    }
}
