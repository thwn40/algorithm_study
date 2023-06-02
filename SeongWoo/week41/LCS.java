package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LCS {

    public static int dp(int[][] memorization, String firstString, String secondString) {

        for (int i = 1; i < memorization.length; i++) {
            for (int j = 1; j < memorization[0].length; j++) {
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    memorization[i][j] = memorization[i - 1][j - 1] + 1;
                } else {
                    memorization[i][j] = Math.max(memorization[i - 1][j], memorization[i][j - 1]);
                }
            }
        }

        return memorization[memorization.length - 1][memorization[0].length - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String firstString = st.nextToken();

        st = new StringTokenizer(br.readLine());
        String secondString = st.nextToken();

        int[][] memorization = new int[firstString.length() + 1][secondString.length() + 1];

        int result = dp(memorization, firstString, secondString);
        System.out.println(result);
    }
}
