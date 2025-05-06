package Lesson_4.instrument;

public class Trumpet implements Instrument {
    String name;
    int diametr;

    public Trumpet(String name, int diametr) {
        this.name = name;
        this.diametr = diametr;
    }

    @Override
    public void play() {
        System.out.println("Trumpet " + name + " play diametr " + diametr);
    }
}
