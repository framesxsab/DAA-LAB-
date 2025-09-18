import java.util.Scanner;
import java.util.Arrays;

public class lcs {

	// Task 1: 
	public static String computeLCS(String X, String Y) {
		int m = X.length();
		int n = Y.length();
		int[][] dp = new int[m + 1][n + 1];
		char[][] dir = new char[m + 1][n + 1]; // D,L, U

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					dir[i][j] = 'D';
				} else if (dp[i - 1][j] >= dp[i][j - 1]) {
					dp[i][j] = dp[i - 1][j];
					dir[i][j] = 'U';
				} else {
					dp[i][j] = dp[i][j - 1];
					dir[i][j] = 'L';
				}
			}
		}

		//  cost matrix with directions
		System.out.println("Cost matrix (value + direction):");
		System.out.print("    ");
		for (int j = 0; j < n; j++) System.out.printf(" %3c", Y.charAt(j));
		System.out.println();
		for (int i = 0; i <= m; i++) {
			if (i == 0) System.out.print("  "); else System.out.printf("%2c", X.charAt(i - 1));
			for (int j = 0; j <= n; j++) {
				String s = Integer.toString(dp[i][j]);
				char d = dir[i][j] == '\u0000' ? ' ' : dir[i][j];
				System.out.printf(" %2s%c", s, d);
			}
			System.out.println();
		}

		// LCS
		StringBuilder sb = new StringBuilder();
		int i = m, j = n;
		while (i > 0 && j > 0) {
			if (dir[i][j] == 'D') {
				sb.append(X.charAt(i - 1));
				i--; j--;
			} else if (dir[i][j] == 'U') {
				i--;
			} else {
				j--;
			}
		}
		return sb.reverse().toString();
	}

	// Task 2: (LRS) via LCS of S with itself, with i != j
	public static String computeLRS(String S) {
		int n = S.length();
		int[][] dp = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int i = n, j = n;
		while (i > 0 && j > 0) {
			if (S.charAt(i - 1) == S.charAt(j - 1) && i != j && dp[i][j] == dp[i - 1][j - 1] + 1) {
				sb.append(S.charAt(i - 1));
				i--; j--;
			} else if (dp[i - 1][j] >= dp[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//  TASK-1
		String X = "AGCCCTAAGGCTACCTAGCTT";
		String Y = "GACAGCCTACAAGCGTTAGCTTG";
		System.out.println("TASK-1 LCS between X and Y");
		System.out.println("X = " + X);
		System.out.println("Y = " + Y);
		String lcs = computeLCS(X, Y);
		System.out.println("Length of LCS: " + lcs.length());
		System.out.println("LCS: " + lcs);

		System.out.println();

		// Example for TASK-2
		String S = "AABCBDC";
		System.out.println("TASK-2 LRS for S = " + S);
		String lrs = computeLRS(S);
		System.out.println("Length of LRS: " + lrs.length());
		System.out.println("LRS: " + lrs);

		sc.close();
	}
}
