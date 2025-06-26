package Lesson_14.BD.repository;

import Lesson_14.BD.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeacherRepository {

    public void createTableTeacherIfNotExist(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE if exists teacher Cascade");
        statement.execute("""
                CREATE TABLE IF NOT exists teacher(
                \tid bigserial PRIMARY KEY,
                \tname text NOT NULL,
                \tage int NOT NULL\s
                );""");
        statement.execute("create index if not exists teacher_id_idt on teacher (id);");
    }

    public void createTableGroupsToTeacherIfNotExists(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("""
                CREATE TABLE IF NOT EXISTS groups_to_teacher(
                \t group_id bigint references "group" ("id"),
                \t teacher_id bigint references teacher ("id")\s
                );""");
        statement.execute("create index if not exists groups_id_group on groups_to_teacher (group_id);");
        statement.execute("create index if not exists teachers_id_teacher on groups_to_teacher (teacher_id);");
    }

    public void insert(Connection connection, Collection<Teacher> teachers) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO teacher (name, age) values " + toInsertValuesTeacher(teachers) + ";");
    }

    public void updateTeacher(Connection connection, Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE FROM teacher SET name = ? WHERE id = ?;"
        );
        preparedStatement.setString(1, teacher.getName());
        preparedStatement.setLong(2, teacher.getId());
    }

    public void deleteByName(Connection connection, Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM teacher WHERE name = ?;");
        preparedStatement.setString(1, teacher.getName());
    }

    public List<Teacher> foundTeachersByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM teacher where name = ?");
        preparedStatement.setString(1, name);
        try (ResultSet rs = preparedStatement.executeQuery()) {
            List<Teacher> teachers = toTeacherList(rs);
            if (teachers.isEmpty()) {
                throw new RuntimeException("Teacher not found by name: " + name);
            }
            return teachers;
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
