package Lesson_7;

import java.util.Objects;

public class Driver extends Person implements Cloneable{
    private int experience;

    public Driver(String lastName, String firstName, String surName, int age, int experience) {
        super(lastName, firstName, surName, age);
        this.experience = experience;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return experience == driver.experience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "experience=" + experience +
                "} " + super.toString();
    }
}
