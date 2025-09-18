// import java.util.*;

// abstract class Node implements Comparable<Node> {
//     public int frequency;
//     public char data;
//     public Node left, right;

//     public Node(int freq) {
//         frequency = freq;
//     }

//     public int compareTo(Node tree) {
//         return frequency - tree.frequency;
//     }
// }

// class HuffmanLeaf extends Node {
//     public HuffmanLeaf(int freq, char val) {
//         super(freq);
//         data = val;
//     }
// }

// class HuffmanNode extends Node {
//     public HuffmanNode(Node l, Node r) {
//         super(l.frequency + r.frequency);
//         left = l;
//         right = r;
//     }
// }

// public class Solution {

//     public static Map<Character, String> mapA = new HashMap<>();

//     public static Node buildTree(int[] charFreqs) {
//         PriorityQueue<Node> trees = new PriorityQueue<>();

//         for (int i = 0; i < charFreqs.length; i++) {
//             if (charFreqs[i] > 0) {
//                 trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));
//             }
//         }

//         while (trees.size() > 1) {
//             Node a = trees.poll();
//             Node b = trees.poll();
//             trees.offer(new HuffmanNode(a, b));
//         }

//         return trees.poll();
//     }

//     public static void printCodes(Node tree, StringBuffer prefix) {
//         if (tree instanceof HuffmanLeaf) {
//             HuffmanLeaf leaf = (HuffmanLeaf) tree;
//             mapA.put(leaf.data, prefix.toString());
//         } else if (tree instanceof HuffmanNode) {
//             HuffmanNode node = (HuffmanNode) tree;

//             prefix.append('0');
//             printCodes(node.left, prefix);
//             prefix.deleteCharAt(prefix.length() - 1);

//             prefix.append('1');
//             printCodes(node.right, prefix);
//             prefix.deleteCharAt(prefix.length() - 1);
//         }
//     }

//     public static void decodeHuff(Node root, String s) {
//         Node current = root;
//         StringBuilder decoded = new StringBuilder();

//         for (int i = 0; i < s.length(); i++) {
//             current = (s.charAt(i) == '0') ? current.left : current.right;

//             if (current.left == null && current.right == null) {
//                 decoded.append(current.data);
//                 current = root;
//             }
//         }

//         System.out.println(decoded.toString());
//     }

//     public static void main(String[] args) {
//         Scanner input = new Scanner(System.in);
//         String test = input.next();

//         int[] charFreqs = new int[256];
//         for (char c : test.toCharArray()) {
//             charFreqs[c]++;
//         }

//         Node tree = buildTree(charFreqs);
//         printCodes(tree, new StringBuffer());

//         StringBuffer encoded = new StringBuffer();
//         for (int i = 0; i < test.length(); i++) {
//             encoded.append(mapA.get(test.charAt(i)));
//         }

//         decodeHuff(tree, encoded.toString());
//     }
// }
import java.util.Scanner;

public class IPSubnettingCalculator
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        
            System.out.println("IP Subnetting Calculator");
            System.out.println("1. Enter IP Address and Subnet Mask");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
            {
                System.out.print("Enter IP Address (e.g., 192.168.1.1): ");
                String ipAddress = sc.next();
                System.out.print("Enter Subnet Mask (e.g., 255.255.255.0): ");
                String subnetMask = sc.next();
                calculateSubnetting(ipAddress, subnetMask);
            }
            case 2: 
            {
                break;
            }
            default:
            {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    public static void calculateSubnetting(String ipAddress, String subnetMask)
    {
        String[] ip = ipAddress.split("\\.");
        String[] mask = subnetMask.split("\\.");
        int[] networkId = new int[4];
        int[] broadcastId = new int[4];
        int[] firstAddress = new int[4];
        int[] lastAddress = new int[4];
        int totalAddresses, usableAddresses;

        for (int i = 0; i < 4; i++)
        {
            networkId[i] = Integer.parseInt(ip[i]) & Integer.parseInt(mask[i]);
            broadcastId[i] = Integer.parseInt(ip[i]) | (255 - Integer.parseInt(mask[i]));
        }

        firstAddress[0] = networkId[0];
        firstAddress[1] = networkId[1];
        firstAddress[2] = networkId[2];
        firstAddress[3] = networkId[3] + 1;

        lastAddress[0] = broadcastId[0];
        lastAddress[1] = broadcastId[1];
        lastAddress[2] = broadcastId[2];
        lastAddress[3] = broadcastId[3] - 1;

        totalAddresses = (int) Math.pow(2, getHostBits(mask)) - 2;
        usableAddresses = totalAddresses - 2;

        System.out.println("Network ID : " + formatAddress(networkId));
        System.out.println("Subnet Mask : " + subnetMask);
        System.out.println("First Address : " + formatAddress(firstAddress));
        System.out.println("Last Address : " + formatAddress(lastAddress));
        System.out.println("Broadcast Address : " + formatAddress(broadcastId));
        System.out.println("Usable Addresses : " + usableAddresses);
        System.out.println("Available Addresses : " + totalAddresses);
        System.out.println("Range of Address : " + );

    }

    public static int getHostBits(String[] mask)
    {
        int hostBits = 0;
        for (int i = 0; i < 4; i++)
        {
            int maskOctet = Integer.parseInt(mask[i]);
            for (int j = 0; j < 8; j++)
            {
                if ((maskOctet & (1 << (7 - j))) == 0)
                {
                    hostBits++;
                }
            }
        }
        return hostBits;
    }

    public static String formatAddress(int[] address)
    {
        return address[0] + "." + address[1] + "." + address[2] + "." + address[3];
    }
}
