package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 오르막_수_11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());

        int[][] memorization = new int[dist + 1][10];

        Arrays.fill(memorization[1], 1);

        for (int i = 2; i <= dist; i++) {
            memorization[i][0] = 1;
            for (int j = 1; j <= 9; j++) {
                memorization[i][j] = (memorization[i][j - 1] + memorization[i - 1][j]) % 10007;
            }
        }

        int sum = Arrays.stream(memorization[dist]).sum();
        System.out.println(sum % 10007);
    }
}
