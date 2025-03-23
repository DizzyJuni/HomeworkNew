package Lesson_4.instrument;

public class Main {
    public static void main(String[] args) {
        instrument[] instruments = new instrument[3];
        instruments[0] = new guitar("Electro", 6);
        instruments[1] = new drum("Metal", 20);
        instruments[2] = new trumpet("Saxaphon", 15);
        playInstruments(instruments);
    }

    public static void playInstruments(instrument[] instruments) {
        for (int i = 0; i < instruments.length; i++) {
            instruments[i].play();
        }
    }
}