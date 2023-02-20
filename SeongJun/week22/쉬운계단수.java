package SeongJun.week22;

import java.io.IOException;
import java.util.Scanner;

public class 쉬운계단수 {
	private static int MOD = 1000000000;
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[][] dp = new long[101][10];

		dp[0][0] = 0;
		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if(j==0){
					dp[i][0]=dp[i-1][1]%MOD;
				}
				else if(j==9){
					dp[i][9]=dp[i-1][8]%MOD;
				}
				else{
					dp[i][j]=(dp[i-1][j-1]%MOD+dp[i-1][j+1]%MOD)%MOD;
				}

			}

		}

		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum+=dp[N][i]%MOD;
		}
		System.out.println(sum);
	}
}
