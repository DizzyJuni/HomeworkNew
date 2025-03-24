package Lesson_2.fibonachi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input number:");
        int n = scanner.nextInt();
        System.out.println("Number Fibonachi" + " " + n + " = " + f(n));
    }

    private static int f(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }
}

