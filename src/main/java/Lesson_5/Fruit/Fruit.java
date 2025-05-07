package Lesson_5.Fruit;

public abstract class Fruit {
    private final double weight;
    private final double price;
    private static double totalPrice;

    public Fruit(double weight, double price) {
        this.weight = weight;
        this.price = price;
        totalPrice += price();
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public static double totalPrice () {
        return totalPrice;
    }

    public abstract double price();

}



