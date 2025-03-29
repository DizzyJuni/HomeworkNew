package Lesson_5.Fruit;

public class Pear extends Fruit {
    private static int totalPrice;

    public Pear(int weight, int price) {
        super(weight, price);
        totalPrice += price();
    }

    public static int totalPearPrice() {
        return totalPrice;
    }

    @Override
    public int price() {
        return getPrice() * getWeight();
    }

}
