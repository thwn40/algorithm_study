package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 타일링_2xn_11726 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());

        int[] memorization = new int[dist + 2];
        memorization[1] = 1;
        memorization[2] = 2;

        for (int i = 3; i <= dist; i++) {
            memorization[i] = (memorization[i - 1] + memorization[i - 2]) % 10007;
        }

        System.out.println(memorization[dist]);
    }
}
