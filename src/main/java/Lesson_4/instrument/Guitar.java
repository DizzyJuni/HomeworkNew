package Lesson_4.instrument;

public class Guitar implements Instrument {
    String name;
    int numberOfString;

    public Guitar(String name, int numberOfString) {
        this.name = name;
        this.numberOfString = numberOfString;
    }

    @Override
    public void play() {
        System.out.println("Guitar " + name + " play string " + numberOfString);
    }
}
