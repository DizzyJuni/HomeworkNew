package Lesson_14.jdbc.model;

public class Student {
    private Long id;
    private String name;
    private int age;
    private Float schoolarship;
    private Long groupId;

    public Student(long id, String name, int age, Float schoolarship, Long groupId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.schoolarship = schoolarship;
        this.groupId = groupId;
    }

    public Student(String name, int age, Float schoolarship, Long groupId) {
        this.name = name;
        this.age = age;
        this.schoolarship = schoolarship;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Float getSchoolarship() {
        return schoolarship;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSchoolarship(Float schoolarship) {
        this.schoolarship = schoolarship;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", schoolarship=" + schoolarship +
                ", groupId=" + groupId +
                '}';
    }
}