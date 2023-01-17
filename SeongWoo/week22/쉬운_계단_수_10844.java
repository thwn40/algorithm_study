package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 쉬운_계단_수_10844 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());
        long mod = 1000000000L;

        long[][] memorization = new long[dist + 1][11];

        Arrays.fill(memorization[1], 1);
        memorization[1][0] = 0L;
        memorization[1][10] = 0L;

        //점화식
        //memorization[n][m] = 길이가 n이고, 끝이 m인 계단수의 개수
        //memorization[n][m] = memorization[n - 1][m - 1] + memorization[n - 1][m + 1]
        for (int i = 2; i <= dist; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    memorization[i][j] = memorization[i - 1][j + 1] % mod;
                } else {
                    memorization[i][j] = (memorization[i - 1][j - 1]  + memorization[i - 1][j + 1]) % mod;
                }
            }
        }

        long result = Arrays.stream(memorization[dist]).sum();
        System.out.println(result % mod);
    }
}
