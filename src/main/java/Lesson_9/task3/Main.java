package Lesson_9.task3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add(3, "5");
        array.add("6");
        array.add("7");
        array.add("8");
        array.add("9");
        array.add("10");
        array.remove(4);

        System.out.println(array);
    }
}
