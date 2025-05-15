package Lesson_2.palindrom_v2;

public class Main {
    public static void main(String[] args) {
        String str1 = "kokokok";
        System.out.print(equals(str1));

    }

    public static boolean equals(String str1) {
        char[] chars = str1.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
