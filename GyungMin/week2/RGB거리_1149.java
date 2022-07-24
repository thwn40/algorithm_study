package GyungMin.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리_1149 {

    public static int[][] paintingCost;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        paintingCost = new int[N][3];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            paintingCost[i][0] = Integer.parseInt(st.nextToken());
            paintingCost[i][1] = Integer.parseInt(st.nextToken());
            paintingCost[i][2] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = paintingCost[0][0];
        dp[0][1] = paintingCost[0][1];
        dp[0][2] = paintingCost[0][2];

        // ex) dp[N-1][red]   : Math.min(dp[N-2][green], dp[N-2][blue]) + paintingCost[N-1](중에 제일 작은 값)
        //     dp[N-1][green] : Math.min(dp[N-2][red], dp[N-2][blue]) + paintingCost[N-1](중에 제일 작은 값)
        //     dp[N-1][blue]  : Math.min(dp[N-2][red], dp[N-2][green]) + paintingCost[N-1](중에 제일 작은 값)

        System.out.println(Math.min(paintingDp(N - 1, 0), Math.min(paintingDp(N - 1, 1), paintingDp(N - 1, 2))));
    }

    static int paintingDp(int idx, int color) {
        if (dp[idx][color] == 0) {
            if (color == 0) {
                dp[idx][0] = Math.min(paintingDp(idx - 1, 1), paintingDp(idx - 1, 2)) + paintingCost[idx][0];
            } else if (color == 1) {
                dp[idx][1] = Math.min(paintingDp(idx - 1, 0), paintingDp(idx - 1, 2)) + paintingCost[idx][1];
            } else  {
                dp[idx][2] = Math.min(paintingDp(idx - 1, 0), paintingDp(idx - 1, 1)) + paintingCost[idx][2];
            }
        }
        return dp[idx][color];
    }
}
