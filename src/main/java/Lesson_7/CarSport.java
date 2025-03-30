package Lesson_7;

import java.util.Objects;

public class CarSport extends Car {
    private final int speedLimit;

    public CarSport(String model, int weight, Driver driver, Engine engine, int power, String company, int speedLimit) {
        super(model, weight, driver, engine, power, company);
        this.speedLimit = speedLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarSport carSport = (CarSport) o;
        return speedLimit == carSport.speedLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speedLimit);
    }

}
