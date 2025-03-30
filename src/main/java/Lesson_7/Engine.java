package Lesson_7;

import java.util.Objects;

public class Engine {
    private int power;
    private String company;

    public Engine(int power, String company) {
        this.power = power;
        this.company = company;
    }

    public static void start() {
        System.out.println("Engine is starting");
    }

    public static void stop() {
        System.out.println("Engine is stopping");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return power == engine.power && Objects.equals(company, engine.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, company);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "power=" + power +
                ", company='" + company + '\'' +
                '}';
    }
}
