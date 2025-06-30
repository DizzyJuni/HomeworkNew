package Lesson_9.task2;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);
        System.out.println("Размер  " + stack.size());
        System.out.println("Забрал и удалил " + stack.pop());
        System.out.println("Размер  " + stack.size());
        System.out.println("Использую " + stack.peek());
        System.out.println("Размер " + stack.size());
        System.out.println(stack);

    }
}
