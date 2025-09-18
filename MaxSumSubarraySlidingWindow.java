import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}

public class MaxSumSubarraySlidingWindow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solution solution = new Solution();

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("The array must contain at least one element.");
            scanner.close();
            return;
        }

        int[] nums = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Output: " + solution.maxSubArray(nums));
        
        scanner.close();
    }
}
