package Lesson_14.BD.service;

import Lesson_14.BD.model.Faculty;
import Lesson_14.BD.repository.FacultyRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final DataSource dataSource;

    public FacultyService(DataSource dataSource, FacultyRepository facultyRepository) {
        this.dataSource = dataSource;
        this.facultyRepository = facultyRepository;
    }

    public void createTableIfNotExist(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            facultyRepository.createTableFacultyIfNotExists(connection);
            System.out.println("Таблица Faculty создана.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания таблицы Faculty.", e);
        }
    }

    public void insertFaculty(Collection<Faculty> faculties) {
        try (Connection connection = dataSource.getConnection()) {
            facultyRepository.insertFaculty(connection, faculties);
            System.out.println("Успешное добавление.");
        } catch (SQLException e) {
            throw new RuntimeException("Добавление не успешно.", e);
        }
    }

    public void update(DataSource dataSource, Faculty faculty) {
        try (Connection connection = dataSource.getConnection()) {
            facultyRepository.updateFaculty(connection, faculty);
            System.out.println("Изменение успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Изменение не успешно.", e);
        }
    }

    public void deleteByName(DataSource dataSource, Faculty faculty) {
        try (Connection connection = dataSource.getConnection()) {
            facultyRepository.deleteByName(connection, faculty);
            System.out.println("Удаление по имени " + faculty.getName() + " успешно");
        } catch (SQLException e) {
            throw new RuntimeException("Удаление по имени " + faculty.getName() + " неуспешно", e);
        }
    }

    public Faculty findByName(String name) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
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
