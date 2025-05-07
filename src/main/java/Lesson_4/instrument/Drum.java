package Lesson_4.instrument;

public class Drum implements Instrument {
    String name;
    int size;

    public Drum(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void play() {
        System.out.println("Drum " + name + " play size " + size);
    }
}
