package Lesson_9.task1;

public class Main {
    public static void main(String[] args) {
        ListPrinter<Integer> stringListPrinter = new ListPrinter<>();
        stringListPrinter.add(1);
        stringListPrinter.add(2);
        stringListPrinter.add(3);
        stringListPrinter.add(4);
        stringListPrinter.add(5);
        stringListPrinter.add(6);
        stringListPrinter.add(7);
        stringListPrinter.add(8);

        stringListPrinter.printList(true);
        stringListPrinter.printList(false);


    }
}
