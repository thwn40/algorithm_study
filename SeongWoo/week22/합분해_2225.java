package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합분해_2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int mod = 1000000000;

        int[][] memorization = new int[k + 1][n + 1];

        Arrays.fill(memorization[1], 1);

        for (int i = 2; i <= k; i++) {
            memorization[i][0] = 1;
            for (int j = 1; j <= n; j++) {
                memorization[i][j] = (memorization[i][j - 1] + memorization[i - 1][j]) % mod;
            }
        }

        System.out.println(memorization[k][n] % mod);
    }
}
