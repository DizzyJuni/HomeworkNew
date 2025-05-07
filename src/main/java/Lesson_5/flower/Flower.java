package Lesson_5.flower;

public class Flower {
    static int counter;
    String country;
    int price;

    public Flower(String country, int price) {
        this.country = country;
        this.price = price;

    }

    public int getPrice() {
        Flower.counter++;
        return price;
    }
}

