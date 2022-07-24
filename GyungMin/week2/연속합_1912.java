package GyungMin.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class 연속합_1912 {
    static int[] dp = {};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] caseArr = new int[N];
        dp = new int[N];
        caseArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp[0] = caseArr[0];
        int max = caseArr[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + caseArr[i], caseArr[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
