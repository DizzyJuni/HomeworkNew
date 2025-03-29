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

class Rose extends Flower {

    public Rose(String country, int price) {
        super(country, price);
    }
}

class Tulips extends Flower {

    Tulips(String country, int price) {
        super(country, price);
    }
}

