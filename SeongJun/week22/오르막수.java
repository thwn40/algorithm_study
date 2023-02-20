package SeongJun.week22;

import java.util.Scanner;

public class 오르막수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[][] dp = new long[1002][10];

		for (int i = 0; i < 10; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i <= n + 1; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < j + 1; k++) {
					dp[i][j] += dp[i - 1][k] % 10007;
				}
			}
		}
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += dp[n - 1][i];
		}
		System.out.println(sum % 10007);
	}
}
