package Lesson_9.task2;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> stack = new ArrayList<>();

    public void push(T element) {
        stack.add(element);
    }

    public T pop() {
        return stack.remove(size() - 1);
    }

    public T peek() {
        return stack.get(size() - 1);
    }

    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stack=" + stack +
                '}';
    }
}
