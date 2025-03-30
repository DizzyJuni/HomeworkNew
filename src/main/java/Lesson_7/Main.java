package Lesson_7;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Driver dima = new Driver("Vetrov", "Dmitrii", "Viktorovish", 34, 12);
        Driver lena = new Driver("Prisyaznyuk", "Elena", "Anatolevna", 33, 13);
        Engine changan = new Engine(181, "Changan");
        Engine g4bc = new Engine(106, "Hyundai");
        Driver cloned = (Driver) dima.clone();
        System.out.println("Ogriginal= " + dima);
        System.out.println("Clone= " + cloned);
        System.out.println(dima.equals(cloned));
        System.out.println(dima == cloned);
        System.out.println(dima.hashCode());
        System.out.println(cloned.hashCode());


        Car hyundai = new Car("Accent", 1000, dima, g4bc, 106, "Hyundai");
        CarSport changanUniV = new CarSport("UniV", 1400, dima, changan, 181, "Changan", 300);
        CarSport changanUniK = new CarSport("UniK", 1400, lena, changan, 220, "Changan", 320);
        changanUniV.start();
        changanUniV.turnLeft();
        changanUniV.stop();
        System.out.println(changanUniV);
        System.out.println(hyundai);

    }
}
