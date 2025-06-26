package Lesson_14.BD;

import Lesson_14.BD.configuration.ConnectionConfiguration;
import Lesson_14.BD.model.Faculty;
import Lesson_14.BD.model.Group;
import Lesson_14.BD.model.Student;
import Lesson_14.BD.model.Teacher;
import Lesson_14.BD.repository.*;
import Lesson_14.BD.service.*;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(connectionConfiguration.getUrl());
        dataSource.setUser(connectionConfiguration.getUsername());
        dataSource.setPassword(connectionConfiguration.getPassword());

        FacultyRepository facultyRepository = new FacultyRepository();
        GraduateRepository graduateRepository = new GraduateRepository();
        GroupRepository groupRepository = new GroupRepository();
        StudentRepository studentRepository = new StudentRepository();
        TeacherRepository teacherRepository = new TeacherRepository();

        FacultyService facultyService = new FacultyService(dataSource, facultyRepository);
        GraduateService graduateService = new GraduateService(dataSource, graduateRepository);
        GroupService groupService = new GroupService(dataSource, groupRepository);
        StudentService studentService = new StudentService(dataSource, studentRepository, graduateRepository);
        TeacherService teacherService = new TeacherService(dataSource, teacherRepository);

        facultyService.createTableIfNotExist(dataSource);
        graduateService.createTableIfNotExists(dataSource);
        groupService.createTableIfNotExists(dataSource);
        studentService.createTableIfNotExists(dataSource);
        teacherService.createTableIfNotExists(dataSource);
        teacherService.createTableGroupsToTeacherIfNotExists(dataSource);
        System.out.println("-----------------");

        Collection<Faculty> faculties = List.of(new Faculty("История"), new Faculty("Философия"));
        Group group = new Group("Группа", 1L);
        Student student = new Student("Петров", 25);
        Teacher teacher = new Teacher("Иванова", 27);
        studentService.insert(student);

        System.out.println("Проверка Faculty");
        facultyService.insertFaculty(faculties);
        Faculty updateFaculty = facultyService.findByName("История");
        updateFaculty.setName("Химия");
        facultyService.update(dataSource, updateFaculty);
        facultyService.deleteByName(dataSource, updateFaculty);
        System.out.println("----------------");

        System.out.println("Проверка Group");
        groupService.insert(dataSource, List.of(group));
        Group updateGroup = groupService.findGroupByName(dataSource, "Группа");
        updateGroup.setName("Измененная группа");
        groupService.update(dataSource, updateGroup);
//        groupService.delete(dataSource,updateGroup);
        System.out.println("----------------");

        System.out.println("Проверка Teacher");
        teacherService.insert(dataSource, List.of(teacher));
        Teacher updateTeacher = teacherService.foundTeacherByName(dataSource, "Иванова");
        updateTeacher.setName("Захаров");
        teacherService.update(dataSource, updateTeacher);
        teacherService.delete(dataSource, updateTeacher);
        System.out.println("----------------");

        System.out.println("Проверка Студент");
        studentService.insert(student);
        Student updateStudent = studentService.findByName(dataSource, "Петров");
        updateStudent.setName("Печенье");
        studentService.update(dataSource, updateStudent);
        studentService.graduation(
                updateStudent,
                "DIPL-2023-001",
                LocalDate.of(2023, 6, 30)
        );
    }
}
