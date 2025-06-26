package Lesson_14.BD.model;

import java.time.LocalDate;

public class Graduate {
    private Long id;
    private String name;
    private String diplomatNumber;
    private LocalDate graduationYear;
    private Faculty faculty;

    public Graduate(Long id, String name, String diplomatNumber, LocalDate graduationYear, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.diplomatNumber = diplomatNumber;
        this.graduationYear = graduationYear;
        this.faculty = faculty;
    }

    public Graduate(String name, String diplomatNumber, LocalDate graduationYear) {
        this.name = name;
        this.diplomatNumber = diplomatNumber;
        this.graduationYear = graduationYear;
    }

    public Graduate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiplomatNumber() {
        return diplomatNumber;
    }

    public void setDiplomatNumber(String diplomatNumber) {
        this.diplomatNumber = diplomatNumber;
    }

    public LocalDate getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(LocalDate graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
