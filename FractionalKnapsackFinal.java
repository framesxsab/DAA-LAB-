

import java.util.Arrays;
import java.util.Comparator;

class Box {
    int id;
    double weight;
    double profit;
    double profitPerWeight;

    public Box(int id, double weight, double profit) {
        this.id = id;
        this.weight = weight;
        this.profit = profit;
        this.profitPerWeight = profit / weight;
    }
}

public class FractionalKnapsackFinal {
    private static final int TRUCK_CAPACITY = 850;

    public static void main(String[] args) {
        Box[] boxes = new Box[50];
        initializeBoxes(boxes);

        System.out.println("FRACTIONAL KNAPSACK - TRUCK LOADING PROBLEM ");
        System.out.println("Truck Capacity: " + TRUCK_CAPACITY + " Kgs\n");

        //  Minimum weight first
        System.out.println("---  1: MINIMUM WEIGHT FIRST ---");
        Arrays.sort(boxes, Comparator.comparingDouble(b -> b.weight));
        double profit1 = fractionalKnapsack(boxes);
        System.out.printf("Total Profit: Rs %.2f\n\n", profit1);

        // Maximum profit first
        System.out.println("--- 2: MAXIMUM PROFIT FIRST ---");
        Arrays.sort(boxes, (b1, b2) -> Double.compare(b2.profit, b1.profit));
        double profit2 = fractionalKnapsack(boxes);
        System.out.printf("Total Profit: Rs %.2f\n\n", profit2);

        //  Profit/Weight ratio first
        System.out.println("---  3: PROFIT/WEIGHT RATIO ---");
        Arrays.sort(boxes, (b1, b2) -> Double.compare(b2.profitPerWeight, b1.profitPerWeight));
        double profit3 = fractionalKnapsack(boxes);
        System.out.printf("Total Profit: Rs %.2f\n\n", profit3);

    
    }

    private static void initializeBoxes(Box[] boxes) {
        double[] weights = {7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3, 8, 15, 42, 9, 0, 42, 47, 52, 32, 26, 48, 55, 6, 29, 84, 2, 4, 18, 56, 7, 29, 93, 44, 71, 3, 86, 66, 31, 65, 0, 79, 20, 65, 52, 13};
        double[] profits = {360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892, 600, 38, 48, 147, 78, 256, 63, 17, 120, 164, 432, 35, 92, 110, 22, 42, 50, 323, 514, 28, 87, 73, 78, 15, 26, 78, 210, 36, 85, 189, 274, 43, 33, 10, 19, 389, 276, 312};

        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box(i + 1, weights[i], profits[i]);
        }
    }

    private static double fractionalKnapsack(Box[] boxes) {
        double totalWeight = 0;
        double totalProfit = 0;

        for (Box box : boxes) {
            if (totalWeight + box.weight <= TRUCK_CAPACITY) {
                totalWeight += box.weight;
                totalProfit += box.profit;
            } else {
                double remainingCapacity = TRUCK_CAPACITY - totalWeight;
                totalProfit += box.profit * (remainingCapacity / box.weight);
                break;
            }
        }

        return totalProfit;
    }
}
