package Lesson_7;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Driver driver1 = new Driver("Vetrov", "Dmitrii", "Viktorovish", 34,12);
        Driver driver2 = new Driver( "Prisyaznyuk", "Elena", "Anatolevna", 33,13);
        Engine changan = new Engine(181, "Changan");
        Engine g4bc = new Engine(106, "Hyundai");

        Driver cloned = (Driver) driver1.clone();
        System.out.println("Ogriginal= " + driver1);
        System.out.println("Clone= " + cloned);
        System.out.println(driver1.equals(cloned));
        System.out.println(driver1 == cloned);
        System.out.println(driver1.hashCode());
        System.out.println(cloned.hashCode());
        System.out.println("-------------");


        Car hyundai = new Car("Accent", 1000, driver1, g4bc);
        CarSport changanUniV = new CarSport("UniV", 1400, driver2, changan, 300);
        CarSport changanUniK = new CarSport("UniK", 1400, driver2, changan,  320);
        changanUniV.start();
        changanUniV.turnLeft();
        changanUniV.stop();
        System.out.println(changanUniV);
        System.out.println(hyundai);
    }
}
