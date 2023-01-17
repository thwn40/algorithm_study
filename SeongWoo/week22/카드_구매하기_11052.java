package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드_구매하기_11052 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());

        int[] price = new int[dist + 1];
        int[] memorization = new int[dist + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= dist; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        //memorization[n] = 카드를 n개살 때 최대 비용
        //memorization[n] = max(memorization[n-a] + price[a]) (0 < a <= n)
        //카드를 n개살 때 최대 비용 = 카드를 n -a개 살때 최대 비용 + a개 들어있는 카드팩을 산 비용의 최대값
        for (int i = 1; i <= dist; i++) {
            int max = 0;
            for (int j = 1; j <= i; j++) {
                max = Math.max(max, memorization[i - j] + price[j]);
            }
            memorization[i] = max;
        }

        System.out.println(memorization[dist]);
    }
}
