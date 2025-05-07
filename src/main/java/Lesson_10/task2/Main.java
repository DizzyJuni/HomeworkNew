package Lesson_10.task2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("1", "2", "3", "111", "5", "7", "8", "9");
        String fifthElementFromEnd = getFifthElementFromEnd(list.iterator());
        System.out.println(fifthElementFromEnd);
    }

    public static String getFifthElementFromEnd(Iterator<String> it) {
        List<String> list = new ArrayList<>();
        while (it.hasNext()) {
            String s = it.next();
            list.add(s);
        }
        return list.get(list.size() - 5);
    }
}
