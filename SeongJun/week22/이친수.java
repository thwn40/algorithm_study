package SeongJun.week22;

import java.util.Scanner;
import java.util.function.IntFunction;

public class 이친수 {
	static IntFunction<Integer> asdf() {
		return x -> x + 1;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			int N = sc.nextInt();
			long dp[] = new long[N+1];
		IntFunction<Integer> asdf = asdf();
		asdf.apply(3);

		dp[1] = dp[2] = 1;
			for(int i=3; i<=N; i++) {
				dp[i] = dp[i-1] + dp[i-2];
			}
			System.out.println(dp[N]);
		}

}
