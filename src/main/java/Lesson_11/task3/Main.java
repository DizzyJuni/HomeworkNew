package Lesson_11.task3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(countVowels("qwerty"));
        System.out.println(mostFrequentLetter("Level from easy"));
    }

    public static int countVowels(String input) {
        int countVowels = 0;
        String string = "aeyuio";
        input = input.toLowerCase();
        char[] str = input.toCharArray();
        char[] array = string.toCharArray();
        for (char s : str) {
            for (char d : array) {
                if (d == s) {
                    countVowels++;
                }
            }
        }
        return countVowels;
    }

    public static char mostFrequentLetter(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        char mostFrequent = ' ';
        int counter = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > counter) {
                counter = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }
}
