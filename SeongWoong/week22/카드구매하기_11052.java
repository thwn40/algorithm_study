import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드구매하기_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        int[] P = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            // i 는 카드의 갯수
            P[i] = cost[i];
            for (int j = 1; j < i; j++) {
                //j는 합을 구하기 위해
                P[i] = Math.max(P[i], P[j] + P[i - j]);
            }
        }
        System.out.println(P[N]);
    }
}
