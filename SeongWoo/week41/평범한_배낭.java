package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한_배낭 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int size = Integer.parseInt(st.nextToken());
        int maxWeight = Integer.parseInt(st.nextToken());

        int[] weightArr = new int[size + 1];
        int[] valueArr = new int[size + 1];
        int[][] memorization = new int[size + 1][maxWeight + 1];

        for (int i = 1; i < size + 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            weightArr[i] = weight;
            valueArr[i] = value;

        }

        for (int i = 1; i < memorization.length; i++) {
            for (int j = 1; j < memorization[0].length; j++) {
                if (j - weightArr[i] >= 0) {
                    memorization[i][j] = Math.max(memorization[i - 1][j], memorization[i - 1][j - weightArr[i]] + valueArr[i]);
                } else {
                    memorization[i][j] = memorization[i - 1][j];
                }
            }
        }

        System.out.println(memorization[memorization.length - 1][memorization[0].length - 1]);
    }
}
