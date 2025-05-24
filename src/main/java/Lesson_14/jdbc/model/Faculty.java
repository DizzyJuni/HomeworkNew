package Lesson_14.jdbc.model;

public class Faculty {
    private Long id;
    private String name;

    public Faculty(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
