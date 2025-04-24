package Lesson_3.robot;

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot(5,5, Robot.Direction.Up);
        System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
        robot.turnLeft();
        System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
        robot.stepForward();
        System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
        robot.moveRobot(robot, 7,-4);
        System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
    }
}
