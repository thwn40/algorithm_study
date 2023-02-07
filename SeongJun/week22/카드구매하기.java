package SeongJun.week22;

import java.io.IOException;
import java.util.Scanner;

public class 카드구매하기 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] prices = new int[N+1];

		for (int i = 1; i <=N; i++) {
			prices[i]= sc.nextInt();
		}
		int[] dp = new int[N+1];

		for (int i = 1; i <= N; i++) {
			System.out.println(i);
			for (int j = i; j <= N; j++) {
				System.out.println("dp["+(j-i)+"] "+"price["+i+"]");
				dp[j]= Math.max(dp[j],dp[j-i]+prices[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
