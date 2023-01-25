package SeongWoong.week23;

import java.util.Scanner;

public class 합분해2_13707 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        long[] dp = new long[N + 1];

        for (int i = 0; i <= N; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < K; i++) {
            for (int j = 0; j <= N; j++) {
                if (j-1<0) continue;
                dp[j] += dp[j - 1];
                dp[j] %= 1000000000;
            }
        }

        System.out.println(dp[N]);
    }
}
