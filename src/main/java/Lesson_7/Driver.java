package Lesson_7;

import java.util.Objects;

public class Driver implements Cloneable {
    private int experience;
    private Person person;

    public Driver(Person person, int experience) {
        this.person = person;
        this.experience = experience;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
       Driver clone = (Driver) super.clone();
       Person personClone = (Person) this.person.clone();
       clone.setPerson(personClone);
       return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return experience == driver.experience && Objects.equals(person, driver.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experience, person);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "experience=" + experience +
                ", person=" + person +
                '}';
    }
}
