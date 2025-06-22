package Lesson_11.test1;

import java.util.Comparator;

public class BiggerBoxSizeComparator implements Comparator<Box> {
    @Override
    public int compare(Box o1, Box o2) {
        if (o1.size() < o2.size()) {
            return 1;
        }
        if (o1.size() > o2.size()) {
            return -1;
        }
        return 0;
    }
}