package Lesson_9.task3;

public class Main {
    public static void main(String[] args) {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add(4, "5");
        System.out.println(array);
        for (String s : array) {
            System.out.println(s);
        }
    }
}
