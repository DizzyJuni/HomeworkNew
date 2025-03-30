package Lesson_7;

import java.util.Objects;

public class Person {
    private String lastName;
    private String firstName;
    private String surName;
    private int age;

    public Person(String lastName, String firstName, String surName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(lastName, person.lastName) && Objects.equals(firstName, person.firstName) && Objects.equals(surName, person.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, surName, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                '}';
    }
}

