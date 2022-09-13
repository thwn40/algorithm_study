package Inhwan.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 동전1_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nk[0], k = nk[1];

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) coins[i] = Integer.parseInt(br.readLine());
        Arrays.sort(coins);

        int[] numOfCases = new int[k+1];
        numOfCases[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= k; i++) numOfCases[i] += numOfCases[i-coin];
        }

        System.out.println(numOfCases[k]);
    }
}

