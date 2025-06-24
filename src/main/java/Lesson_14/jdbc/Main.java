package Lesson_14.jdbc;

import Lesson_14.jdbc.configuration.ConnectionConfiguration;
import Lesson_14.jdbc.configuration.ConnectionManager;
import Lesson_14.jdbc.model.Faculty;
import Lesson_14.jdbc.model.Group;
import Lesson_14.jdbc.model.Student;
import Lesson_14.jdbc.repository.FacultyRepository;
import Lesson_14.jdbc.repository.GroupRepository;
import Lesson_14.jdbc.repository.StudentRepository;
import Lesson_14.jdbc.repository.TeacherRepository;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        ConnectionManager connectionManager = new ConnectionManager(connectionConfiguration);
        StudentRepository studentRepository = new StudentRepository(connectionManager);
        GroupRepository groupRepository = new GroupRepository(connectionManager);
        FacultyRepository facultyRepository = new FacultyRepository(connectionManager);
        TeacherRepository teacherRepository = new TeacherRepository(connectionManager);
        facultyRepository.createTableFacultyIfNotExists();
        groupRepository.createTableGroupIfNotExists();
        studentRepository.createTableStudentIfNotExists();
        teacherRepository.createTableTeacherIfNotExist();
        teacherRepository.createTableGroupsToTeacherIfNotExists();

        facultyRepository.insertFaculty(List.of(
                new Faculty("Economics"), new Faculty("History"), new Faculty("Biology")
        ));
        Faculty economicsFaculty = facultyRepository.findByName("Economics");
        Faculty historyFaculty = facultyRepository.findByName("History");

        groupRepository.insertGroup(List.of(
                        new Group("10", economicsFaculty.getId()),
                        new Group("11", economicsFaculty.getId()),
                        new Group("20", historyFaculty.getId()),
                        new Group("25x", historyFaculty.getId())
                )
        );

        Group tenGroup = groupRepository.findGroupByName("10");
        Group twentyFiveXGroup = groupRepository.findGroupByName("25x");
        List<Student> students = List.of(
                new Student("Tom", 19, 100.30f, tenGroup.getId()),
                new Student("Jack", 20, 130.20f, twentyFiveXGroup.getId()),
                new Student("Luisa", 20, 100f, tenGroup.getId()),
                new Student("Sarah", 21, 0f, null)
        );
        studentRepository.insertStudent(students);

        System.out.println(studentRepository.findAllStudent());
        System.out.println(studentRepository.findByGroupName("10"));
        System.out.println(studentRepository.findByGroupId(null));
        Student updateStudent = studentRepository.findByGroupName("10").getFirst();
        updateStudent.setAge(40);
        updateStudent.setGroupId(null);
        updateStudent.setSchoolarship(null);
        System.out.println("updated student name: " + updateStudent.getName());
        studentRepository.updateStudent(updateStudent);
        System.out.println(studentRepository.findAllStudent());
    }
}
