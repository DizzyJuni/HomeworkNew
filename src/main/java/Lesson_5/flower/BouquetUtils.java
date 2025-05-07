package Lesson_5.flower;

public class BouquetUtils {

    public static int priceOfBouquet(Flower[] bouquet) {
        int priceOfBouquet = 0;
        for (Flower flower : bouquet) {
            priceOfBouquet = priceOfBouquet + flower.getPrice();
        }

        return priceOfBouquet;
    }
}