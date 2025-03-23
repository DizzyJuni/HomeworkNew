package Lesson_3.robot;

class robot {
    public int x = 5;
    public int y = 5;
    public Direction direction = Direction.Up;

    public enum Direction {
        Up,
        Down,
        Left,
        Right
    }

    public robot(int x, int y, Direction direction) {
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
            default:
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
            default:
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
            default:
        }
    }

    public void moveRobot(robot robot, int toX, int toY) {
        int tx = robot.getX();
        int ty = robot.getY();

        if (tx >= toX) {
            while (robot.getDirection() != Direction.Left) {
                turnLeft();
            }
            while (tx != toX) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
                tx--;
            }
        } else {
            while (robot.getDirection() != Direction.Right) {
                turnRight();
            }

            while (tx != toX) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
                tx++;
            }
        }


        if (ty >= toY) {
            while (robot.getDirection() != Direction.Down) {
                turnRight();
            }

            while (ty != toY) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
                ty--;
            }
        } else {
            while (robot.getDirection() != Direction.Up) {
                turnLeft();
            }

            while (ty != toY) {
                stepForward();
                System.out.println("X= " + robot.getX() + " Y= " + robot.getY());
                ty++;
            }
        }
    }
}


