import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 동전1_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        int[] coin = new int[n];
        int[] dp = new int[k+1];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        for (int j = 0; j < n; j++) {
            for (int i = 1; i <= k; i++) {
                // 같은 경우에 1을 추가해준다.
                if (i==coin[j]){
                    dp[i]++;
                }
                // 그보다 큰 경우는 점화식을 적용한다.
                if (i>coin[j]){
                    dp[i] = dp[i] + dp[i-coin[j]];
                }
            }
        }
        System.out.println(dp[k]);
    }
}
