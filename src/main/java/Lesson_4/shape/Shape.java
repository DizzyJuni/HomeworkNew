package Lesson_4.shape;

public interface Shape {
    static void printArea(Shape shape) {
        shape.area();
        System.out.println(shape.area());
    }

    double area();
}

class Square implements Shape {
    double lengthSquare;

    public Square(double lengthSquare) {
        this.lengthSquare = lengthSquare;
    }

    @Override
    public double area() {
        return lengthSquare * lengthSquare;
    }
}

class Circle implements Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle implements Shape {
    double length;
    double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }

}






