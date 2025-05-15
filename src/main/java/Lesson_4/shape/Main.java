package Lesson_4.shape;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(4);
        Circle circle1 = new Circle(7);
        Rectangle rectangle1 = new Rectangle(2, 3);
        printArea(square1);
        printArea(circle1);
        printArea(rectangle1);

    }

    public static void printArea(Shape shape) {
        System.out.println(shape.area());
    }
}
