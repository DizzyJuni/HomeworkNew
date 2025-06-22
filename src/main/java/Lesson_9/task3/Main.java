package Lesson_9.task3;

public class Main {
    public static void main(String[] args) {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        array.add("7");
        array.add("8");
        array.add("9");
        array.add("10");
        array.add("11");

        System.out.println(array);
        for (String s : array) {
            System.out.println(s);
        }
        for (String s : array) {
            System.out.println(s);
        }
    }
}
