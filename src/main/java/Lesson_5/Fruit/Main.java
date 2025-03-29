package Lesson_5.Fruit;

public class Main {
    public static void main(String[] args) {

        final Apple redApple = new Apple(5, 2);
        final Apple greenApple = new Apple(3, 2);
        final Apple yellowApple = new Apple(4, 2);
        final Pear china = new Pear(2, 2);
        final Pear conference = new Pear(3, 5);

        System.out.println(Apple.totalApplePrice());
        System.out.println(Pear.totalPearPrice());
        System.out.println(Fruit.totalPrice());
    }
}
