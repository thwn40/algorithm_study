package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일로_만들기_1463 {
    private static void dp(int dist, int[] memorization) {

        for (int i = 2; i <= dist; i++) {
            //1을 뺐을 때
            memorization[i] = memorization[i - 1] + 1;

            //2로 나뉘어 질 때
            if (i % 2 == 0) {
                memorization[i] = Math.min(memorization[i], memorization[i / 2] + 1);
            }

            //3으로 나뉘어 질 때
            if (i % 3 == 0) {
                memorization[i] = Math.min(memorization[i], memorization[i / 3] + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());

        //memorization[i] = i에서 1로 가는 최소 횟수
        int[] memorization = new int[dist + 1];

        dp(dist, memorization);

        System.out.println(memorization[dist]);
    }
}
