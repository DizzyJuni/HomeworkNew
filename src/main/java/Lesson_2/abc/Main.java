package Lesson_2.abc;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        System.out.println(doubleExpression(0.1, 0.2, 0.3));
        System.out.println(doubleExpression(2, 3, 6));
    }

    public static boolean doubleExpression(double a, double b, double c) {
        return abs(a + b - c) <= 0.0001;
    }
}