package Lesson_4.instrument;

public class Main {
    public static void main(String[] args) {
        Instrument[] instruments = new Instrument[3];
        instruments[0] = new Guitar("Electro", 6);
        instruments[1] = new Drum("Metal", 20);
        instruments[2] = new Trumpet("Saxaphon", 15);
        playInstruments(instruments);
    }

    public static void playInstruments(Instrument[] instruments) {
        for (int i = 0; i < instruments.length; i++) {
            instruments[i].play();
        }
    }
}