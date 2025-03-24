package Lesson_3.constructor;

class Example {
    public String name;
    public int version;

    public Example(int version) {
        this("Nintendo");
        this.version = 3;
    }

    public Example() {
    }

    public Example(String name) {
        this.name = name;
    }

    public Example(String name, int version) {
        this.name = name;
        this.version = version;
    }

    public void say() {
        System.out.println(name + " version " + version);
    }
}
