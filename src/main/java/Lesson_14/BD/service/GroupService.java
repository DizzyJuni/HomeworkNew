package Lesson_14.BD.service;

import Lesson_14.BD.model.Group;
import Lesson_14.BD.repository.GroupRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class GroupService {
    private final DataSource dataSource;
    private final GroupRepository groupRepository;

    public GroupService(DataSource dataSource, GroupRepository groupRepository) {
        this.dataSource = dataSource;
        this.groupRepository = groupRepository;
    }

    public void createTableIfNotExists(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            groupRepository.createTableGroupIfNotExists(connection);
            System.out.println("Таблица Group создана.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создании таблицы Group.", e);
        }
    }

    public void insert(DataSource dataSource, Collection<Group> groups) {
        try (Connection connection = dataSource.getConnection()) {
            groupRepository.insert(connection, groups);
            System.out.println("Добавление успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении.", e);
        }
    }

    public void update(DataSource dataSource, Group group) {
        try (Connection connection = dataSource.getConnection()) {
            groupRepository.update(connection, group);
            System.out.println("Изменение успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при изменении.", e);
        }
    }

    public void delete(DataSource dataSource, String group) {
        try (Connection connection = dataSource.getConnection()) {
            int counter = groupRepository.delete(connection, group);
            if (counter > 0) {
                System.out.println("Удаление " + group + " успешно");
            } else {
                System.out.println(group + " не найден");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка во время удаления", e);
        }
    }

    public Group findGroupByName(DataSource dataSource, String name) {
        try (Connection connection = dataSource.getConnection()) {
            return groupRepository.findGroupByName(connection, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
