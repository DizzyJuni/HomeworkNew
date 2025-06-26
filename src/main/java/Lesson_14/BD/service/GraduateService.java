package Lesson_14.BD.service;

import Lesson_14.BD.model.Graduate;
import Lesson_14.BD.repository.GraduateRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class GraduateService {
    private final DataSource dataSource;
    private final GraduateRepository graduateRepository;

    public GraduateService(DataSource dataSource, GraduateRepository graduateRepository) {
        this.dataSource = dataSource;
        this.graduateRepository = graduateRepository;
    }

    public void createTableIfNotExists(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            graduateRepository.createTableIfNotExists(connection);
            System.out.println("Таблица Graduate создана.");
        } catch (SQLException e) {
            throw new RuntimeException("Таблица Graduate не создана.",e);
        }
    }

    public void insert(Graduate graduate) {
        try (Connection connection = dataSource.getConnection()) {
            graduateRepository.insert(connection, graduate);
            System.out.println("Добавление успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Не успешное добавление.", e);
        }
    }

    public void update(DataSource dataSource, Graduate graduate) {
        try (Connection connection = dataSource.getConnection()) {
            graduateRepository.update(connection, graduate);
            System.out.println("Изменение успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Не успешное изменение.", e);
        }
    }

    public void deleteById(DataSource dataSource, Graduate graduate) {
        try (Connection connection = dataSource.getConnection()) {
            graduateRepository.deleteById(connection, graduate);
            System.out.println("Удаление по ID успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Не успешное удаление.", e);
        }
    }

    public void findAll(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            graduateRepository.findAll(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findById(DataSource dataSource, Graduate graduate) {
        try (Connection connection = dataSource.getConnection()) {
            graduateRepository.findById(connection, graduate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
