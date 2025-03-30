package Lesson_3.robot;

class Robot {
    public int x ;
    public int y ;
    public Direction direction = Direction.Up;

    public enum Direction {
        Up,
        Down,
        Left,
        Right
    }

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void turnLeft() {
        switch (getDirection()) {
            case Up:
                direction = Direction.Left;
                break;
            case Down:
                direction = Direction.Right;
                break;
            case Left:
                direction = Direction.Down;
                break;
            case Right:
                direction = Direction.Up;
                break;
        }
    }

    public void turnRight() {
        switch (getDirection()) {
            case Up:
                direction = Direction.Right;
                break;
            case Down:
                direction = Direction.Left;
                break;
            case Right:
                direction = Direction.Down;
                break;
            case Left:
                direction = Direction.Up;
                break;
        }
    }

    public void stepForward() {
        switch (getDirection()) {
            case Left:
                x--;
                break;
            case Right:
                x++;
                break;
            case Down:
                y--;
                break;
            case Up:
                y++;
                break;
        }
    }

    public void moveRobot(Robot robot, int toX, int toY) {

        if (getX() >= toX) {
            while (robot.getDirection() != Direction.Left) {
                turnLeft();
            }
            while (getX() != toX) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
            }
        } else {
            while (robot.getDirection() != Direction.Right) {
                turnRight();
            }

            while (getX() != toX) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
            }
        }


        if (getY() >= toY) {
            while (robot.getDirection() != Direction.Down) {
                turnRight();
            }

            while (getY() != toY) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
            }
        } else {
            while (robot.getDirection() != Direction.Up) {
                turnLeft();
            }

            while (getY() != toY) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
            }
        }
    }
}


