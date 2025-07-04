package Lesson_14.BD.service;

import Lesson_14.BD.model.Graduate;
import Lesson_14.BD.repository.GraduateRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
            throw new RuntimeException("Таблица Graduate не создана.", e);
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

    public void deleteById(DataSource dataSource, Long id) {
        try (Connection connection = dataSource.getConnection()) {
            int counter = graduateRepository.deleteById(connection, id);
            if (counter > 0) {
                System.out.println("Удаление по ID успешно.");
            } else {
                System.out.println("ID не существует");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не успешное удаление.", e);
        }
    }

    public List<Graduate> findAll(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            return graduateRepository.findAll(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Graduate findById(DataSource dataSource, Long id) {
        try (Connection connection = dataSource.getConnection()) {
            return graduateRepository.findById(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
