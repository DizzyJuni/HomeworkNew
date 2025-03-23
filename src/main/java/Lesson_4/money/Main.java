package Lesson_4.money;

public class Main {
    public static void main(String[] args) {
        Money m1 = new Money(16, 40);
        Money m2 = new Money(3, 90);
        Money m3 = m1.multiply(3);
        Money m4 = m1.minus(m2);
        Money m5 = m2.plus(m1);
        m3.print();
        m4.print();
        m5.print();
    }
}
