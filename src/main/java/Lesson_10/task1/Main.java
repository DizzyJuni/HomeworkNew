package Lesson_10.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Box> list = new LinkedList<>();
        list.add(new Box(10));
        list.add(new Box(20));
        list.add(new Box(30));
        Collection<Box> result = filterBySize(list,30);
        Collection<Box> result2 = filterBySize(list.iterator(), 30);
        System.out.println(result);
        System.out.println("------------------");
        System.out.println(result2);
        System.out.println("------------------");

        PriorityQueue<Box> boxes1 = new PriorityQueue<>();
        boxes1.add(new Box(10));
        boxes1.add(new Box(90));
        boxes1.add(new Box(40));
        boxes1.add(new Box(30));
        boxes1.add(new Box(60));
        while (!boxes1.isEmpty()) {
            System.out.println(boxes1.poll());
        }
        System.out.println("------------------");

        PriorityQueue<Box> boxes = new PriorityQueue<>(new BiggerBoxSizeComparator());
        boxes.add(new Box(10));
        boxes.add(new Box(90));
        boxes.add(new Box(40));
        boxes.add(new Box(30));
        boxes.add(new Box(60));
        while (!boxes.isEmpty()) {
            System.out.println(boxes.poll());
        }

    }

    public static Collection<Box> filterBySize(Collection<Box> collection, int size) {
        return filterBySize(collection.iterator(), size);
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
}
