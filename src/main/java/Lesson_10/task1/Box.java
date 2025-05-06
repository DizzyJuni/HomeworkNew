package Lesson_10.task1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public record Box(int size) implements Comparable<Box> {

    public static Collection<Box> filterBySize(Collection<Box> collection, int size) {
        Collection<Box> list = new ArrayList<>();
        for (Box s : collection) {
            if (s.size() < size) {
                list.add(s);
            }
        }
        return list;
    }

    public static Collection<Box> filterBySize(Iterator<Box> iterator, int size) {
        Collection<Box> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Box s = iterator.next();
            if (s.size() < size) {
                list.add(s);
            }
        }
        return list;
    }

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
