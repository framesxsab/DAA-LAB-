import java.util.Random;

public class TaskA_LinearSearch {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter number of sensor readings (n): ");
        int n = sc.nextInt();
        float[] temperature = new float[n];
        float[] pressure = new float[n];
        Random rand = new Random();

        // random sensor data
        for (int i = 0; i < n; i++) {
            temperature[i] = -20 + rand.nextFloat() * 70; // -20 to 50
            pressure[i] = 950 + rand.nextFloat() * 100;   // 950 to 1050
        }

        // Time for min temperature
        long startTemp = System.nanoTime();
        float minTemp = temperature[0];
        for (float temp : temperature) {
            if (temp < minTemp) minTemp = temp;
        }
        long endTemp = System.nanoTime();
        double timeTemp = (endTemp - startTemp) / 1e6;

        // Time for max pressure
        long startPressure = System.nanoTime();
        float maxPressure = pressure[0];
        for (float pres : pressure) {
            if (pres > maxPressure) maxPressure = pres;
        }
        long endPressure = System.nanoTime();
        double timePressure = (endPressure - startPressure) / 1e6;

        System.out.printf("Linear Search Results:\n");
        System.out.printf("Min Temperature: %.2f (Time: %.3f ms)\n", minTemp, timeTemp);
        System.out.printf("Max Pressure: %.2f (Time: %.3f ms)\n", maxPressure, timePressure);
    }
}