package Lesson_11.test1;

import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Box> set = new TreeSet<>(new BiggerBoxSizeComparator());
        set.add(new Box(10));
        set.add(new Box(20));
        set.add(new Box(30));
        set.add(new Box(40));
        set.add(new Box(50));
        set.add(new Box(60));

        for (Box i : set) {
            System.out.println(i);
        }
    }
}
