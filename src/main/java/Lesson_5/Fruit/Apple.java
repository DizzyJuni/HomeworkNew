package Lesson_5.Fruit;

public class Apple extends Fruit {
    private static double totalPrice;

    public Apple(double weight, double price) {
        super(weight, price);
        totalPrice += price();
    }

    public static double totalApplePrice() {
        return totalPrice;
    }

    @Override
    public double price() {
        return getPrice() * getWeight();
    }

}
