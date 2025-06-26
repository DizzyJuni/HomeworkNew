package Lesson_14.BD.model;

public class Group {
    private Long id;
    private String name;
    private Long faculty_id;

    public Group(Long id, String name, Long faculty_id) {
        this.id = id;
        this.name = name;
        this.faculty_id = faculty_id;
    }

    public Group(String name, Long faculty_id) {
        this.name = name;
        this.faculty_id = faculty_id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getFaculty_id() {
        return faculty_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty_id(Long faculty_id) {
        this.faculty_id = faculty_id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculty_id=" + faculty_id +
                '}';
    }
}
