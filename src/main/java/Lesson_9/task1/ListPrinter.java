package Lesson_9.task1;

import java.util.ArrayList;
import java.util.List;

public class ListPrinter<T> {
    private List<T> list = new ArrayList<>();

    public void add(T element) {
        list.add(element);
    }

    public void printList(boolean isOdd) {
        if (isOdd) {
            for (int i = 0; i < list.size(); i = i + 2)
                System.out.print( list.get(i) + " ");

        } else {
            for (int i = 1; i < list.size(); i = i + 2)
                System.out.print(list.get(i) + " ");
        }
    }
}

