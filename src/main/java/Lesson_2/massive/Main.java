package Lesson_2.massive;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 33, 45, 65};
        int[][][] arr = new int[][][]{{{1, 2}, {98, 4}}, {{5, 6}, {7, 8}, {9, 10}}};
        System.out.println(max(nums));
        System.out.println(min(nums));
        System.out.println(sum(nums));
        System.out.println(max(arr));
    }

    public static int max(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }

    public static int min(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }

    public static int sum(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return sum;
    }

    public static int max(int[][][] arr) {
        int max = arr[0][0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    if (max < arr[i][j][k]) {
                        max = arr[i][j][k];
                    }
                }
            }
        }
        return max;
    }
}