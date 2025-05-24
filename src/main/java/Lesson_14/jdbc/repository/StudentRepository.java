package Lesson_14.jdbc.repository;

import Lesson_14.jdbc.configuration.ConnectionManager;
import Lesson_14.jdbc.model.Group;
import Lesson_14.jdbc.utils.RepositoryUtils;
import Lesson_14.jdbc.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentRepository {

    private final ConnectionManager connectionManager;

    public StudentRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createTableStudentIfNotExists() {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE if exists student");
            statement.execute("""
                    CREATE TABLE IF NOT exists student(
                    \tid bigserial PRIMARY KEY,
                    \tname text NOT NULL,
                    \tage int NOT NULL,
                    \tscholarship numeric(10, 2),
                    \tgroup_id bigint references "group" (id)\s
                    );""");
            statement.execute("create index if not exists student_group_id_idx on student (group_id);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findByGroupId(Long groupId) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = findByNullableGroupIdStatement(connection, groupId)) {
                ResultSet rs = preparedStatement.executeQuery();
                return toStudentList(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Student> findByGroupName(String name) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.* FROM student s " +
                    "JOIN \"group\" g ON s.group_id = g.id where g.name = ?");
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Student> groups = toStudentList(rs);
                if (groups.isEmpty()) {
                    throw new RuntimeException("Group not found by name: " + name);
                }
                return groups;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Student> findAllStudent() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            List<Student> students = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from student ");
            while (resultSet.next()) {
                students.add(toStudent(resultSet));
            }
            return students;
        }
    }

    public void insertStudent(Collection<Student> students) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO student (name, age, scholarship, group_id) values "
                    + toInsertValuesStudent(students) + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    String.format(
                            "UPDATE student set name = '%s', age = %d, scholarship = %s, group_id = %s where id = %d",
                            student.getName(),
                            student.getAge(),
                            student.getSchoolarship(),
                            student.getGroupId(),
                            student.getId()
                    )
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement findByNullableGroupIdStatement(Connection connection, Long group_id) throws
            SQLException {
        if (group_id == null) {
            return connection.prepareStatement("SELECT * FROM student where group_id is null;");
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student where group_id = ?");
            preparedStatement.setLong(1, group_id);
            return preparedStatement;
        }
    }

    private String toInsertValuesStudent(Collection<Student> students) {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = " ";
        for (Student s : students) {
            stringBuilder.append(delimiter)
                    .append("(")
                    .append("'").append(s.getName()).append("'").append(",")
                    .append(s.getAge()).append(",")
                    .append(s.getSchoolarship()).append(",")
                    .append(s.getGroupId())
                    .append(")");
            delimiter = ",";
        }
        return stringBuilder.toString();
    }

    private List<Student> toStudentList(ResultSet rs) throws SQLException {
        List<Student> result = new ArrayList<>();
        while (rs.next()) {
            result.add(toStudent(rs));
        }
        return result;
    }

    private Student toStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("age"),
                RepositoryUtils.getNullableFloat(rs, "scholarship"),
                RepositoryUtils.getNullableLong(rs, "group_id")
        );
    }
}
