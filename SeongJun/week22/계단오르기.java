package SeongJun.week22;

import java.util.Scanner;

public class 계단오르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		int[] arr = new int[i+1];
		int[][] dp = new int[i+1][2];
		for (int i1 = 1; i1 <= i; i1++) {
			arr[i1]= sc.nextInt();
		}

		dp[1][0]=arr[1];

		for (int i1 = 2; i1 <= i; i1++) {
			dp[i1][0]=Math.max(dp[i1-2][0],dp[i1-2][1])+arr[i1];
			dp[i1][1]=dp[i1-1][0]+arr[i1];
		}
		System.out.println(Math.max(dp[i][0],dp[i][1]));
	}
}
