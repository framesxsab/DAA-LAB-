import java.util.Arrays;
import java.util.Scanner;

class SubarrayResult {
    int sum;
    int start;
    int end;

    SubarrayResult(int sum, int start, int end) {
        this.sum = sum;
        this.start = start;
        this.end = end;
    }
}

class MaxSumSubarray { 

    private static SubarrayResult maxCrossingSum(int[] resources, int left, int mid, int right, int constraint) {
        SubarrayResult bestResult = new SubarrayResult(0, -1, -1);

        int currentSum = 0;
        for (int i = mid; i >= left; i--) {
            currentSum += resources[i];

            if (currentSum > constraint) {
                break; 
            }

            if (currentSum > bestResult.sum) {
                bestResult = new SubarrayResult(currentSum, i, mid);
            }

            int rightSum = currentSum;
            for (int j = mid + 1; j <= right; j++) {
                rightSum += resources[j];
                if (rightSum > constraint) {
                    break; 
                }
                if (rightSum > bestResult.sum) {
                    bestResult = new SubarrayResult(rightSum, i, j);
                }
            }
        }
        return bestResult;
    }

    private static SubarrayResult maxSubarraySum(int[] resources, int left, int right, int constraint) {
        if (left > right) {
            return new SubarrayResult(0, -1, -1);
        }
        if (left == right) {
            if (resources[left] <= constraint) {
                return new SubarrayResult(resources[left], left, left);
            } else {
                return new SubarrayResult(0, -1, -1);
            }
        }

        int mid = left + (right - left) / 2;

        SubarrayResult leftResult = maxSubarraySum(resources, left, mid, constraint);
        SubarrayResult rightResult = maxSubarraySum(resources, mid + 1, right, constraint);
        SubarrayResult crossResult = maxCrossingSum(resources, left, mid, right, constraint);

        if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossResult.sum) {
            return leftResult;
        } else if (rightResult.sum >= leftResult.sum && rightResult.sum >= crossResult.sum) {
            return rightResult;
        } else {
            return crossResult;
        }
    }

    public static SubarrayResult findMaxSubarray(int[] resources, int constraint) {
        if (resources == null || resources.length == 0 || constraint <= 0) {
            return new SubarrayResult(0, -1, -1);
        }
        return maxSubarraySum(resources, 0, resources.length - 1, constraint);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of tasks (resources): ");
        int n = scanner.nextInt(); 
        if (n <= 0) {
            System.out.println("Number of tasks must be positive.");
            scanner.close();
            return;
        }
        int[] resources = new int[n];
        System.out.println("Enter the resource values for each task:");
        for (int i = 0; i < n; i++) {
            resources[i] = scanner.nextInt();
        }
        System.out.print("Enter the resource constraint: ");
        int constraint = scanner.nextInt();

        SubarrayResult result = findMaxSubarray(resources, constraint);

        if (result.sum == 0 && result.start == -1) {  
            System.out.println("No feasible subarray found within the constraint.");
        } else {
            System.out.println("Maximum resource utilization: " + result.sum);
            
            int[] subarrayElements = Arrays.copyOfRange(resources, result.start, result.end + 1);
            System.out.println("Subarray elements: " + Arrays.toString(subarrayElements));
            System.out.println("Indices: from " + result.start + " to " + result.end);
        }

        scanner.close();
    }
}
