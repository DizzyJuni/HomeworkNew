package Lesson_14.BD.service;

import Lesson_14.BD.model.Teacher;
import Lesson_14.BD.repository.TeacherRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class TeacherService {
    private final DataSource dataSource;
    private final TeacherRepository teacherRepository;

    public TeacherService(DataSource dataSource, TeacherRepository teacherRepository) {
        this.dataSource = dataSource;
        this.teacherRepository = teacherRepository;
    }

    public void createTableIfNotExists(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            teacherRepository.createTableTeacherIfNotExist(connection);
            System.out.println("Таблица Teacher успешно создана.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы Teacher", e);
        }
    }

    public void createTableGroupsToTeacherIfNotExists(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            teacherRepository.createTableGroupsToTeacherIfNotExists(connection);
            System.out.println("Таблица Groups_to_Teacher успешно создана.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы Groups_to_Teacher.", e);
        }
    }

    public void insert(DataSource dataSource, Collection<Teacher> teachers) {
        try (Connection connection = dataSource.getConnection()) {
            teacherRepository.insert(connection, teachers);
            System.out.println("Добавление успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении.", e);
        }
    }

    public void update(DataSource dataSource, Teacher teacher) {
        try (Connection connection = dataSource.getConnection()) {
            teacherRepository.updateTeacher(connection, teacher);
            System.out.println("Изменение успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при изменении", e);
        }
    }

    public void delete(DataSource dataSource, String teacher) {
        try (Connection connection = dataSource.getConnection()) {
            int counter = teacherRepository.deleteByName(connection, teacher);
            if (counter > 0) {
                System.out.println("Удаление успешно " + teacher);
            } else {
                System.out.println("Ошибка при удалении " + teacher);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении.", e);
        }
    }

    public Teacher findFirstTeacherByName(DataSource dataSource, String name) {
        try (Connection connection = dataSource.getConnection()) {
            return teacherRepository.findFirstTeacherByName(connection, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teacher> foundTeachersByName(DataSource dataSource, String name) {
        try (Connection connection = dataSource.getConnection()) {
            return teacherRepository.foundTeachersByName(connection, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
