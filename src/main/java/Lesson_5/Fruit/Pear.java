package Lesson_5.Fruit;

public class Pear extends Fruit {
    private static double totalPrice;

    public Pear(double weight, double price) {
        super(weight, price);
        totalPrice += price();
    }

    public static double totalPearPrice() {
        return totalPrice;
    }

    @Override
    public double price() {
        return getPrice() * getWeight();
    }



}
