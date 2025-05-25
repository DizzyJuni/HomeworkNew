package Lesson_11.task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(countVowels("qwerty"));
        System.out.println(mostFrequentLetter("Level from easy,,,,,,"));
    }

    public static int countVowels(String input) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('y');
        vowels.add('u');
        vowels.add('i');
        vowels.add('o');
        int countVowels = 0;
        String lowerCase = input.toLowerCase();
        for (char c : lowerCase.toCharArray()) {
            if (vowels.contains(c))
                countVowels++;
        }
        return countVowels;
    }

    public static char mostFrequentLetter(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerChar = Character.toLowerCase(c);
                if (map.containsKey(lowerChar)) {
                    map.put(lowerChar, map.get(lowerChar) + 1);
                } else {
                    map.put(lowerChar, 1);
                }
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
