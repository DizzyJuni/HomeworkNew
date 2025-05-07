package Lesson_11.task2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(twoSum(nums, 10)));
        System.out.println(Arrays.toString(twoSumMap(nums, 19)));
        System.out.println(Arrays.toString(twoSumSet(nums, 19)));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i : nums) {
            for (int j : nums) {
                if (i + j == target) {
                    System.out.println("Числа в массиве");
                    return new int[]{i, j};
                }
            }
        }
        throw new IndexNotFound("Нет чисел в массиве для получения суммы " + target);
    }

    public static int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (indexMap.containsKey(x)) {
                System.out.println("Индексы чисел в массиве");
                return new int[]{indexMap.get(x), i};
            }
            indexMap.put(nums[i], i);
        }
        throw new IndexNotFound("Нет чисел в массиве для получения суммы " + target);
    }

    public static int[] twoSumSet(int[] nums, int target) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            int x = target - num;
            if (numSet.contains(x)) {
                System.out.println("Числа в массиве");
                return new int[]{x, num};
            }
            numSet.add(num);
        }
        throw new IndexNotFound("Нет чисел в массиве для получения суммы " + target);
    }
}
