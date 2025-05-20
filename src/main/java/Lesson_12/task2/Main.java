package Lesson_12.task2;

import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        processor.processInts(numbers, integer -> integer % 2 == 0,  integer -> integer * 2, integer -> System.out.println("i: " + integer));
    }
}
