package Lesson_14.BD.service;

import Lesson_14.BD.model.Graduate;
import Lesson_14.BD.model.Student;
import Lesson_14.BD.repository.GraduateRepository;
import Lesson_14.BD.repository.StudentRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentService {
    private final DataSource dataSource;
    private final StudentRepository studentRepository;
    private final GraduateRepository graduateRepository;

    public StudentService(DataSource dataSource, StudentRepository studentRepository,
                          GraduateRepository graduateRepository) {
        this.dataSource = dataSource;
        this.studentRepository = studentRepository;
        this.graduateRepository = graduateRepository;
    }

    public void createTableIfNotExists(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            studentRepository.createTableStudentIfNotExists(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Student student) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO student (name, age) VALUES (?,?);");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.executeUpdate();
            System.out.println("Студент добавлен успешно");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении студента", e);
        }
    }

    public void update(DataSource dataSource, Student student) {
        try (Connection connection = dataSource.getConnection()) {
            studentRepository.updateStudent(connection, student);
            System.out.println("Изменение успешно.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при изменении", e);
        }
    }

    public List<Student> findAll(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            return studentRepository.findAllStudent(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student findFirstByName(DataSource dataSource, String name) {
        try (Connection connection = dataSource.getConnection()) {
            return studentRepository.findFirstByName(connection, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void graduation(Student student, String diplomaNumber, LocalDate graduationYear) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            studentRepository.delete(connection, student.getName());

            Graduate graduate = new Graduate();
            graduate.setName(student.getName());
            graduate.setDiplomatNumber(diplomaNumber);
            graduate.setGraduationYear(graduationYear);
            graduateRepository.insert(connection, graduate);
            connection.commit();
            System.out.printf("Студент %s успешно выпущен с дипломом %s",
                    student.getName(),
                    diplomaNumber);
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException("Ошибка при откате транзакции" + ex.getMessage(), ex);
                }
            }
            throw new RuntimeException("Ошибка при выпуске студента" + e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
