package Lesson_10.task1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public record Box(int size) implements Comparable<Box> {

    @Override
    public String toString() {
        return "Box{" +
                "size=" + size +
                '}';
    }

    @Override
    public int compareTo(Box o) {
        if (this.size > o.size) {
            return 1;
        }
        if (this.size < o.size) {
            return -1;
        }
        return 0;
    }
}
