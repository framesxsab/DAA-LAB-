public class SqrtComparison {

    /**
     * Calculating  the integer square root of a number using the Babylonian method.
     * Time Complexity for Babylonian Method: O(log(log n))*
     **/
    public static long sqrtBabylonian(int n) {
        if (n < 0) throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        if (n == 0) return 0;

        // initial guess (n lete hai generally )
        long x = n;
        while (true) {
            long next_x = (x + n / x) / 2;
            if (next_x >= x) {
                return x;
            }
            x = next_x;
        }
    }

    /**
     * Calculating  integer square root of a number using Binary Search.
     * TC: O(log n) ham div use karenge instead of multiplication
     **/
    public static long sqrtBinarySearch(int n) {
        if (n < 0) throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        if (n < 2) return n;

        long low = 1;
        long high = n;
        long result = 0;

        while (low <= high) {

            long mid = low + (high - low) / 2;
            
            
            if (mid <= n / mid) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int number = Integer.MAX_VALUE; //bade value ke liye 
        System.out.println("Finding integer square root of: " + number);
        System.out.println("------------------------------------------");

        // ---  Babylonian Method ---
        long startTimeB = System.nanoTime();
        long resultB = sqrtBabylonian(number);
        long endTimeB = System.nanoTime();
        long durationB = endTimeB - startTimeB;

        System.out.println("Babylonian Method ");
        System.out.println("Result: " + resultB);
        System.out.println("Time taken: " + durationB + " nanoseconds.");
        System.out.println();

        // --- Binary Search Method ---
        long startTimeBS = System.nanoTime();
        long resultBS = sqrtBinarySearch(number);
        long endTimeBS = System.nanoTime();
        long durationBS = endTimeBS - startTimeBS;
        
        System.out.println("Binary Search Method ");
        System.out.println("Result: " + resultBS);
        System.out.println("Time taken: " + durationBS + " nanoseconds.");
        System.out.println("------------------------------------------");
        
        double factor = (double) durationBS / durationB;
        System.out.printf("Conclusion: The Babylonian method was %.2f times faster for this input.%n", factor);
    }
}