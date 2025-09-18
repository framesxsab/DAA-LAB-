import java.util.*;

public class FreckleConnectorHeap {
    static class Node implements Comparable<Node> {
        int vertex;
        double key;
        Node(int vertex, double key) {
            this.vertex = vertex;
            this.key = key;
        }
        public int compareTo(Node other) {
            return Double.compare(this.key, other.key);
        }
    }

    public static void primMST_Heap(double[][] graph, int n) {
        int[] parent = new int[n];
        double[] key = new double[n];
        boolean[] mstSet = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(key, Double.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            if (mstSet[u]) continue;
            mstSet[u] = true;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                    pq.add(new Node(v, key[v]));
                }
            }
        }
        printMST(parent, graph, n);
    }

    public static void printMST(int[] parent, double[][] graph, int n) {
        double totalDistance = 0;
        System.out.println("\nFreckle Connections (Minimum Ink Usage):");
        for (int i = 1; i < n; i++) {
            String from = "F" + (parent[i] + 1);
            String to = "F" + (i + 1);
            double dist = graph[i][parent[i]];
            System.out.printf("%s - %s: %.2f\n", from, to, dist);
            totalDistance += dist;
        }
        System.out.printf("Total Ink Used: %.2f\n", totalDistance);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of freckles: ");
        int n = sc.nextInt();
        double[][] graph = new double[n][n];
        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = sc.nextDouble();
        sc.close();
        primMST_Heap(graph, n);
    }
}