package Lesson_10.task2;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" , "12");
        String fifthElementFromEnd = getFifthElementFromEnd(list.iterator());
        System.out.println(fifthElementFromEnd);
    }

    public static String getFifthElementFromEnd(Iterator<String> it) {
        String[] list = new String[5];
        int index = 0;
        while (it.hasNext()) {
            String current = it.next();
            list[index] = current;
            index = (index + 1) % 5;
        }
        return list[index] ;
    }
}
