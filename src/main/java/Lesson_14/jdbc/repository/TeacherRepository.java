package Lesson_14.jdbc.repository;

import Lesson_14.jdbc.configuration.ConnectionManager;
import Lesson_14.jdbc.model.Group;
import Lesson_14.jdbc.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeacherRepository {
    private ConnectionManager connectionManager;

    public TeacherRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createTableTeacherIfNotExist() {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE if exists teacher");
            statement.execute("""
                    CREATE TABLE IF NOT exists teacher(
                    \tid bigserial PRIMARY KEY,
                    \tname text NOT NULL,
                    \tage int NOT NULL\s
                    );""");
            statement.execute("create index if not exists teacher_id_idt on teacher (id);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTableGroupsToTeacherIfNotExists() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("""
                    CREATE TABLE IF NOT exists groups_to_teacher(
                    \t group_id bigint references group ("id")
                    \t teacher_id bigint references teacher ("id")\s
                    );""");
            statement.execute("create index if not exists groups_id_group on groups_to_teacher (group_id);");
            statement.execute("create index if not exists teachers_id_teacher on groups_to_teacher (teacher_id);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertTeacher(Collection<Teacher> teachers) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO teacher (name, age) values " + toInsertValuesTeacher(teachers) + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    String.format(
                            "UPDATE teacher set name = %s, age = %d",
                            teacher.getName(),
                            teacher.getAge()
                    )
            );
        }
    }

    public List<Teacher> foundTeacherByName(String name) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teacher where name = ?");
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Teacher> teachers = toTeacherList(rs);
                if (teachers.isEmpty()) {
                    throw new RuntimeException("Teacher not found by name: " + name);
                }
                return teachers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String toInsertValuesTeacher(Collection<Teacher> teachers) {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = " ";
        for (Teacher t : teachers) {
            stringBuilder.append(delimiter)
                    .append("(")
                    .append("'").append(t.getName()).append("'").append(",")
                    .append(t.getAge())
                    .append(")");
            delimiter = ",";
        }
        return stringBuilder.toString();
    }

    private List<Teacher> toTeacherList(ResultSet rs) throws SQLException {
        List<Teacher> result = new ArrayList<>();
        while (rs.next()) {
            result.add(toTeacher(rs));
        }
        return result;
    }

    private Teacher toTeacher(ResultSet rs) throws SQLException {
        return new Teacher(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("age")
        );
    }
}
