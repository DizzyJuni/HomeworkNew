package Lesson_5.Fruit;

public abstract class Fruit {
    private int weight;
    private int price;
    private static int totalPrice;

    public Fruit(int weight, int price) {
        this.weight = weight;
        this.price = price;
        totalPrice += price();
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public static int totalPrice () {
        return totalPrice;
    }

    public abstract int price();

}



