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

    public void deleteByName(DataSource dataSource, String faculty) {
        try (Connection connection = dataSource.getConnection()) {
            int counter = facultyRepository.deleteByName(connection, faculty);
            if (counter > 0) {
                System.out.println("Удаление по имени " + faculty + " успешно");
            } else {
                System.out.println("Факультет " + faculty + " не найден");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Удаление по имени " + faculty + " неуспешно", e);
        }
    }

    public Faculty findByName(DataSource dataSource, String name) {
        try (Connection connection = dataSource.getConnection()) {
            return facultyRepository.findByName(connection, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
