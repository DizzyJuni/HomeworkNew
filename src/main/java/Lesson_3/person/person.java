package Lesson_3.person;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    void talk(String speech) {
        System.out.println(name + " say " + speech);
    }

    void move(String destination) {
        System.out.println(name + " move to " + destination);
    }
}
