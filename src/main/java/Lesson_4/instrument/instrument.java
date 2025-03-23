package Lesson_4.instrument;

public interface instrument {
    void play();
}

class guitar implements instrument {
    String name;
    int numberOfString;

    public guitar(String name, int numberOfString) {
        this.name = name;
        this.numberOfString = numberOfString;
    }

    @Override
    public void play() {
        System.out.println("Guitar " + name + " play string " + numberOfString);
    }
}

class drum implements instrument {
    String name;
    int size;

    public drum(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void play() {
        System.out.println("Drum " + name + " play size " + size);
    }
}

class trumpet implements instrument {
    String name;
    int diametr;

    public trumpet(String name, int diametr) {
        this.name = name;
        this.diametr = diametr;
    }

    @Override
    public void play() {
        System.out.println("Trumpet " + name + " play diametr " + diametr);
    }
}
