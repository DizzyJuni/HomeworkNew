package Lesson_14.BD.model;

public class Student {
    private Long id;
    private String name;
    private int age;
    private Float scholarship;
    private Long groupId;

    public Student(long id, String name, int age, Float scholarship, Long groupId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.scholarship = scholarship;
        this.groupId = groupId;
    }

    public Student(String name, int age, Float scholarship, Long groupId) {
        this.name = name;
        this.age = age;
        this.scholarship = scholarship;
        this.groupId = groupId;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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

    public Float getScholarship() {
        return scholarship;
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

    public void setScholarship(Float scholarship) {
        this.scholarship = scholarship;
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
                ", schoolarship=" + scholarship +
                ", groupId=" + groupId +
                '}';
    }
}