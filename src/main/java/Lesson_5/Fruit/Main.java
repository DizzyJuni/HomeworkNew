package Lesson_5.Fruit;

public class Main {
    public static void main(String[] args) {

        final Apple redApple = new Apple(5.0, 2.1);
        final Apple greenApple = new Apple(3.2, 2.3);
        final Apple yellowApple = new Apple(4.7, 2.9);
        final Pear china = new Pear(2.9, 2.2);
        final Pear conference = new Pear(34, 5.1);

        System.out.printf("%.2f%n", redApple.getPrice());
        System.out.printf("%.2f%n", Apple.totalApplePrice());
        System.out.printf("%.2f%n", Pear.totalPearPrice());
        System.out.printf("%.2f%n", Fruit.totalPrice());
    }
}
