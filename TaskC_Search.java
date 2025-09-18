import java.util.Arrays;
import java.util.Random;

public class TaskC_Search {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter number of temperature readings (n): ");
        int n = sc.nextInt();
        float[] temp = new float[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            temp[i] = 20 + rand.nextFloat() * 30; // 20 to 50
        }

        Arrays.sort(temp); // Sorted data

        // Linear Search
        long start1 = System.nanoTime();
        int linearIndex = -1;
        for (int i = 0; i < n; i++) {
            if (temp[i] >= 30) {
                linearIndex = i;
                break;
            }
        }
        long end1 = System.nanoTime();
        double timeLinear = (end1 - start1) / 1e6;

        // Binary Search
        long start2 = System.nanoTime();
        int binaryIndex = binarySearchFirstOccurrence(temp, 30);
        long end2 = System.nanoTime();
        double timeBinary = (end2 - start2) / 1e6;

        System.out.printf("Linear Search: First index >= 30: %d (Time: %.3f ms)\n", linearIndex, timeLinear);
        System.out.printf("Binary Search: First index >= 30: %d (Time: %.3f ms)\n", binaryIndex, timeBinary);
    }

    static int binarySearchFirstOccurrence(float[] arr, float target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
