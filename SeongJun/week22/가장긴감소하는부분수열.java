package SeongJun.week22;

import java.util.Arrays;
import java.util.Scanner;

public class 가장긴감소하는부분수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] arr = new int[T];
		for (int i = 0; i < T; i++) {
			arr[i] = sc.nextInt();
		}

		int[] dp = new int[T + 1];
		for (int i = 0; i < T; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {

				if (arr[j] > arr[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}

			}

		}
		// System.out.println(Arrays.toString(dp));

		System.out.println(Arrays.stream(dp).max().getAsInt());

	}
}

