package SeongJun.week22;

import java.util.Scanner;

public class 스티커 {
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);

			int T = sc.nextInt();
			for (int i = 0; i < T; i++) {
				int N = sc.nextInt();
				int[][] arr = new int[2][N+1];
				int[][] dp = new int[3][N+1];

				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < N; k++) {
						arr[j][k]= sc.nextInt();
					}
				}

				dp[1][1]=arr[0][0];
				dp[2][1]=arr[1][0];

				for (int j = 2; j <=N; j++) {
					dp[0][j-1]=Math.max(dp[1][j-2],dp[2][j-2]);
					dp[1][j]=Math.max(dp[0][j-1],dp[2][j-1])+arr[0][j-1];
					dp[2][j]=Math.max(dp[0][j-1],dp[1][j-1])+arr[1][j-1];

				}
				int max = 0;
				for (int[] ints : dp) {
					for (int anInt : ints) {
						max=Math.max(max,anInt);
					}
				}
				// System.out.println(Arrays.deepToString(dp));
				System.out.println(max);
			}
		}
	}


