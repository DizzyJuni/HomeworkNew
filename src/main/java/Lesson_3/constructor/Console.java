package Lesson_3.constructor;

class Console {
    public String name;
    public int version;

    public Console(int version) {
        this.version = version;
    }

    public Console() {
    }

    public Console(String name) {
        this.name = name;
    }

    public Console(String name, int version) {
        this.name = name;
        this.version = version;
    }

    public void say() {
        System.out.println(name + " version " + version);
    }
}