package SeongJun.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		/**
		 *  10 -4 3 1 5 6 -35 12 21
		 *  dp1 10
		 *  dp2 10-4,-4 = 6
		 *  dp3 6+3, 3 = 9

		 */

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numbers = new int[N];

		int[] dp = new int[N];


		int idx = 0;
		while (st.hasMoreTokens()) {
			numbers[idx] = (Integer.parseInt(st.nextToken()));
			idx++;
		}
		dp[0] = numbers[0];

		int max=numbers[0];

		for (int i = 1; i < N; i++) {
			dp[i]=Math.max(dp[i-1]+numbers[i],numbers[i]);

			max = Math.max(max,dp[i]);
		}
		System.out.println(max);


	}
}
