package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일이삼_더하기_9095 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tryCount = Integer.parseInt(st.nextToken());
        int[] memorization = new int[11];
        memorization[0] = 1;
        memorization[1] = 1;
        memorization[2] = 2;

        for (int i = 0; i < tryCount; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());

            for (int j = 3; j <= dist; j++) {
                if (memorization[j] != 0) {
                    continue;
                }
                //점화식
                memorization[j] = memorization[j - 1] + memorization[j - 2] + memorization[j - 3];
            }

            System.out.println(memorization[dist]);
        }
    }
}
