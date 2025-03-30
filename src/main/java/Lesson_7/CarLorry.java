package Lesson_7;

import java.util.Objects;

public class CarLorry extends Car {
    public final int carrying;

    public CarLorry(String model, int weight, Driver driver, Engine engine, int power, String company, int carrying) {
        super(model, weight, driver, engine);
        this.carrying = carrying;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarLorry carLorry = (CarLorry) o;
        return carrying == carLorry.carrying;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carrying);
    }

}
