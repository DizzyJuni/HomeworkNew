package Lesson_7;

public class Car {
    private final String model;
    private final int weight;
    private Driver driver;
    private Engine engine;

    public Car(String model, int weight, Driver driver, Engine engine) {

        this.model = model;
        this.weight = weight;
        this.driver = driver;
        this.engine = engine;
    }


    public void start() {
        Engine.start();
        System.out.println("Car is starting");
    }


    public void stop() {
        Engine.stop();
        System.out.println("Car is stopping");
    }

    public void turnLeft() {
        System.out.println("Going to left");
    }

    public void turnRight() {
        System.out.println("Going to right");
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", weight=" + weight +
                ", driver=" + driver +
                ", engine=" + engine +
                '}';
    }
}
