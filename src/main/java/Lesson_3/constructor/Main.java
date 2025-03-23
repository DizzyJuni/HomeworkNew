package Lesson_3.constructor;

public class Main {

    public static void main(String[] args) {
        Console Console = new Console();
        Console Console3 = new Console(3);
        Console Console1 = new Console("Xbox", 1);
        Console Console2 = new Console("Playstation", 2);
        Console.say();
        Console1.say();
        Console2.say();
        Console3.say();
    }
}
