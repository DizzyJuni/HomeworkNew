package Lesson_5.flower;

public class Main {

    public static void main(String[] args) {
        final Rose hollandRose = new Rose("Holland", 20);
        final Rose japanRose = new Rose("Japan", 30);
        final Tulips simple = new Tulips("Russia", 10);
        final Flower[] bouquet = new Flower[] {hollandRose,japanRose, simple};
        final Flower[] bouquet2 = new Flower[] {japanRose,japanRose,japanRose,hollandRose};

        System.out.println("Цена за букет " + BouquetUtils.priceOfBouquet(bouquet));
        System.out.println("Цена за букет " + BouquetUtils.priceOfBouquet(bouquet2));
        System.out.println("Всего продали цветов " + Flower.counter);

    }
}
