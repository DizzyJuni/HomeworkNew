package Lesson_5.flower;

public class BouquetUtils {

    public static int priceOfBouquet(Flower[] bouquet) {
        int priceOfBouquet = 0;
        for (int i = 0; i < bouquet.length; i++) {
            priceOfBouquet = priceOfBouquet + bouquet[i].getPrice();
        }

        return priceOfBouquet;
    }
}