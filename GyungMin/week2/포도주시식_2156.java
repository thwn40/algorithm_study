package GyungMin.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 포도주시식_2156 {
    static int[] dp ;
    static int[] grapeArr ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        dp = new int[N+1];
        grapeArr = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            grapeArr[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println("Arrays.toString(grapeArr) = " + Arrays.toString(grapeArr));
        for (int i = 1; i < N+1; i++) {
            drinkGrape(i);
        }
        System.out.println(dp[N]);
    }

    static int drinkGrape(int N) {
        if (N == 1) {
            dp[1] = grapeArr[1];
        } else if (N == 2) {
            dp[2] = grapeArr[1] + grapeArr[2];
        } else if (N == 3) {
            dp[3] = Math.max(dp[2], Math.max((grapeArr[1] + grapeArr[3]), (grapeArr[2] + grapeArr[3])));
        } else {
            dp[N] = Math.max((dp[N - 3] + grapeArr[N - 1] + grapeArr[N]), Math.max(dp[N - 1], dp[N - 2] + grapeArr[N]));
        }
        return dp[N];
    }
}