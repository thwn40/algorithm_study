import java.util.Scanner;

public class RGB거리_1149 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        //N : 집의 수
        int N = sc.nextInt();
        // home[i][3] : i번째 집을 칠하는 3가지 비용
        int[][] home = new int[N][3];

        for (int i = 0; i < N; i++) {
            home[i][0] = sc.nextInt();
            home[i][1] = sc.nextInt();
            home[i][2] = sc.nextInt();
        }
        // dp[i][0] : i번째 집에서 0번 페인트를 칠한경우 중에 최솟값 (i-1번째 집에서 1번or2번 페인트를 칠함)
        // dp[i][1] : i번째 집에서 1번 페인트를 칠한경우 최솟값 (i-1번째 집에서 0번or2번 페인트를 칠함)
        // dp[i][2] : i번째 집에서 2번 페인트를 칠한경우 최솟값 (i-1번째 집에서 0번or1번 페인트를 칠함)
        int[][] dp = new int[N][3];
        dp[0][0] = home[0][0];
        dp[0][1] = home[0][1];
        dp[0][2] = home[0][2];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i-1][1]+home[i][0],dp[i-1][2]+home[i][0]);
            dp[i][1] = Math.min(dp[i-1][0]+home[i][1],dp[i-1][2]+home[i][1]);
            dp[i][2] = Math.min(dp[i-1][0]+home[i][2],dp[i-1][1]+home[i][2]);
        }
        System.out.println(Math.min(dp[N-1][0],Math.min(dp[N-1][1],dp[N-1][2])));
    }
}
