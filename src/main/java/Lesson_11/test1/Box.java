package Lesson_11.test1;

public record Box(int size) {

    @Override
    public String toString() {
        return "Box{" +
                "size=" + size +
                '}';
    }
}
