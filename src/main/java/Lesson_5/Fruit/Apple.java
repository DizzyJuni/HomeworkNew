package Lesson_5.Fruit;

public class Apple extends Fruit {
    private static int totalPrice;

    public Apple(int weight, int price) {
        super(weight, price);
        totalPrice += price();
    }

    public static int totalApplePrice() {
        return totalPrice;
    }

    @Override
    public int price() {
        return getPrice() * getWeight();
    }

}
