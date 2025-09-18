import java.util.Random;

public class TaskB_Pairwise {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter number of sensor readings (n): ");
        int n = sc.nextInt();
        float[] temperature = new float[n];
        float[] pressure = new float[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            temperature[i] = -20 + rand.nextFloat() * 70;
            pressure[i] = 950 + rand.nextFloat() * 100;
        }

        // Time for min temperature (pairwise)
        long startTemp = System.nanoTime();
        float minTemp = 0;
        for (int i = 0; i < n; i++) {
            boolean isMin = true;
            for (int j = 0; j < n; j++) {
                if (temperature[j] < temperature[i]) {
                    isMin = false;
                    break;
                }
            }
            if (isMin) {
                minTemp = temperature[i];
                break;
            }
        }
        long endTemp = System.nanoTime();
        double timeTemp = (endTemp - startTemp) / 1e6;

        // Time for max pressure (pairwise)
        long startPressure = System.nanoTime();
        float maxPressure = 0;
        for (int i = 0; i < n; i++) {
            boolean isMax = true;
            for (int j = 0; j < n; j++) {
                if (pressure[j] > pressure[i]) {
                    isMax = false;
                    break;
                }
            }
            if (isMax) {
                maxPressure = pressure[i];
                break;
            }
        }
        long endPressure = System.nanoTime();
        double timePressure = (endPressure - startPressure) / 1e6;

        System.out.printf("Pairwise Comparison Results:\n");
        System.out.printf("Min Temperature: %.2f (Time: %.3f ms)\n", minTemp, timeTemp);
        System.out.printf("Max Pressure: %.2f (Time: %.3f ms)\n", maxPressure, timePressure);
    }
}
