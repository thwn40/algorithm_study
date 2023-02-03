package SeongWoong.week23;

import java.util.Scanner;

public class 합분해_2225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        //dp[K][N] 는 K개의 수를 더해서 N이 되는 경우
        long[][] dp = new long[K + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= N; k++) {
                    if (j - k < 0) continue;
                    dp[i][j] += dp[i - 1][j - k];
                    dp[i][j] %= 1000000000;
                }
            }
        }
        System.out.println(dp[K][N]);
    }
}
