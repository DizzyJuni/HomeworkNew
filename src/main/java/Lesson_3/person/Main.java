package Lesson_3.person;

public class Main {

    public static void main(String[] args) {
        Person person = new Person("Tom", 50);
        String speech = "Hello";
        String destination = "home";
        person.talk(speech);
        person.move(destination);
    }
}

