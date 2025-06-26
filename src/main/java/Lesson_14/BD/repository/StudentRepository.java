package Lesson_14.BD.repository;

import Lesson_14.BD.utils.RepositoryUtils;
import Lesson_14.BD.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentRepository {

    public void createTableStudentIfNotExists(final Connection connection) throws SQLException {
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
    }

    public void delete(final Connection connection, String studentName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM student WHERE name = ?;");
        preparedStatement.setString(1, studentName);
        preparedStatement.executeUpdate();
    }

    public void insertStudent(final Connection connection, Collection<Student> students) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO student (name, age, scholarship, group_id) values "
                + toInsertValuesStudent(students) + ";");
    }

    public void updateStudent(final Connection connection, Student student) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
                String.format(
                        "UPDATE student set name = '%s', age = %d, scholarship = %s, group_id = %s where id = %d",
                        student.getName(),
                        student.getAge(),
                        student.getScholarship(),
                        student.getGroupId(),
                        student.getId()
                )
        );
    }

    public Student findByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM student WHERE name = ?;"
        );
        preparedStatement.setString(1, name);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getFloat("scholarship"),
                        resultSet.getLong("group_id")
                );
            }
        }
        throw new RuntimeException("Студент не найден");
    }

    public List<Student> findByGroupId(final Connection connection, Long groupId) throws SQLException {
        try (PreparedStatement preparedStatement = findByNullableGroupIdStatement(connection, groupId)) {
            ResultSet rs = preparedStatement.executeQuery();
            return toStudentList(rs);
        }
    }

    public void findById(final Connection connection, Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT FROM student WHERE id = ?;");
        preparedStatement.setLong(1, student.getId());
        preparedStatement.executeUpdate();
    }

    public List<Student> findByGroupName(final Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.* FROM student s " +
                "JOIN \"group\" g ON s.group_id = g.id where g.name = ?");
        preparedStatement.setString(1, name);
        try (ResultSet rs = preparedStatement.executeQuery()) {
            List<Student> groups = toStudentList(rs);
            if (groups.isEmpty()) {
                throw new RuntimeException("Group not found by name: " + name);
            }
            return groups;
        }
    }

    public List<Student> findAllStudent(final Connection connection) throws SQLException {
        List<Student> students = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from student ");
        while (resultSet.next()) {
            students.add(toStudent(resultSet));
        }
        return students;
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
                    .append(s.getScholarship()).append(",")
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
