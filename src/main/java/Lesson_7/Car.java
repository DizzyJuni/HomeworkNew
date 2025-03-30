package Lesson_7;

public class Car extends Engine {
    private final String model;
    private final int weight;
    private Driver driver;
    private Engine engine;

    public Car(String model, int weight, Driver driver, Engine engine, int power, String company) {
        super(power, company);
        this.model = model;
        this.weight = weight;
        this.driver = driver;
        this.engine = engine;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine){
        this.engine = engine;
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Car is starting");
    }

    @Override
    public void stop() {
        super.stop();
        System.out.println("Car is stopping");
    }

    public void turnLeft() {
        System.out.println("Going to left");
    }

    public void turnRight() {
        System.out.println("Going to right");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Car clone = (Car) super.clone();
        Driver driverClone = (Driver) this.driver.clone();
        clone.setDriver(driverClone);
        Engine engineClone = (Engine) this.engine.clone();
        clone.setEngine(engineClone);
        return clone;
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
