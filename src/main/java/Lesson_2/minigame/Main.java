package Lesson_2.minigame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Math.random();
        int i = (int) (Math.random() * 100);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int input_number = scanner.nextInt();
            if (input_number == i) {
                System.out.println("You win");
                break;
            } else if (input_number > i) {
                System.out.println("Number less");
            } else {
                System.out.println("Number more");
            }
        }
    }
}

