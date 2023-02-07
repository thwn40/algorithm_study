package SeongJun.week22;

import java.util.Scanner;

public class 일이삼더하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			long[] dp = new long[210];
			dp[1]=1;
			dp[2]=2;
			dp[3]=4;
			for (int j = 4; j <= N; j++) {
				dp[j]=dp[j-1]+dp[j-2]+dp[j-3];
			}

			System.out.println(dp[N]);

		}
	}
}
