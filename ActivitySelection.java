import java.util.Arrays;
import java.util.*;

class Activity {
    int start, finish, name, profit;


    public Activity(int start, int finish, int name, int profit) {
        this.start = start;
        this.finish = finish;
        this.name = name;
        this.profit = profit;
    }
}


class MaxSumSubarray {


    static void sortByFinish(Activity[] arr) {
        Arrays.sort(arr, (a1, a2) -> a1.finish - a2.finish);
 Arrays.sort(arr, (a1, a2) -> a1.start - a2.start);


        
    }


    static int selectActivities(Activity[] arr, int[] selected) {
        sortByFinish(arr);
        int n = arr.length;
        int totalProfit = 0;
        int k = 0;
        int lastSelectedFinishTime = -1;


        for (int i = 0; i < n; i++) {
            if (arr[i].start >= lastSelectedFinishTime) {
           
                selected[k++] = arr[i].name;
                totalProfit += arr[i].profit;
                lastSelectedFinishTime = arr[i].finish;
            }
        }
        return totalProfit;
    }


    public static void main(String[] args) {
        Activity[] arr = {
            new Activity(1, 4, 1, 10),
            new Activity(3, 5, 2, 15),
            new Activity(0, 6, 3, 14),
            new Activity(5, 7, 4, 12),
            new Activity(3, 9, 5, 20),
            new Activity(5, 9, 6, 30),
            new Activity(6, 10, 7, 32),
            new Activity(8, 11, 8, 28),
            new Activity(8, 12, 9, 30),
            new Activity(2, 14, 10, 40),
            new Activity(12, 16, 11, 45)
        };


        int[] selectedActivityNames = new int[arr.length];
        int totalProfit = selectActivities(arr, selectedActivityNames);


        System.out.println("Selected activities:");
        for (int activityName : selectedActivityNames) {
            if (activityName != 0) {
                System.out.print("A" + activityName + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }
}

class MaxSubarrayDivideConquer {

    // Helper function to find the maximum crossing subarray
    private static int maxCrossingSum(int[] resources, int left, int mid, int right, int constraint) {
        int leftSum = 0, maxLeftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            leftSum += resources[i];
            if (leftSum <= constraint) {
                maxLeftSum = Math.max(maxLeftSum, leftSum);
            }
        }

        int rightSum = 0, maxRightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            rightSum += resources[i];
            if (rightSum <= constraint) {
                maxRightSum = Math.max(maxRightSum, rightSum);
            }
        }

        return Math.max(maxLeftSum, Math.max(maxRightSum, maxLeftSum + maxRightSum));
    }

    // Recursive function to find the maximum subarray sum
    private static int maxSubarraySum(int[] resources, int left, int right, int constraint) {
        if (left == right) {
            return resources[left] <= constraint ? resources[left] : Integer.MIN_VALUE;
        }

        int mid = (left + right) / 2;

        int leftMax = maxSubarraySum(resources, left, mid, constraint);
        int rightMax = maxSubarraySum(resources, mid + 1, right, constraint);
        int crossMax = maxCrossingSum(resources, left, mid, right, constraint);

        return Math.max(leftMax, Math.max(rightMax, crossMax));
    }

    public static int findMaxSubarray(int[] resources, int constraint) {
        if (resources == null || resources.length == 0 || constraint <= 0) {
            return 0; // No feasible subarray
        }

        int maxSum = maxSubarraySum(resources, 0, resources.length - 1, constraint);
        return maxSum == Integer.MIN_VALUE ? 0 : maxSum;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testResources = {
            {2, 1, 3, 4},
            {2, 2, 2, 2},
            {1, 5, 2, 3},
            {6, 7, 8},
            {1, 2, 3, 2, 1},
            {1, 1, 1, 1, 1},
            {4, 2, 3, 1},
            {},
            {1, 2, 3},
            new int[100000]
        };

        // Fill large test case
        for (int i = 0; i < 100000; i++) {
            testResources[9][i] = i + 1;
        }

        int[] constraints = {5, 4, 5, 5, 5, 4, 5, 10, 0, 1000000000};

        for (int i = 0; i < testResources.length; i++) {
            System.out.println("Test Case " + (i + 1) + ": Max Sum = " + findMaxSubarray(testResources[i], constraints[i]));
        }
    }
}
