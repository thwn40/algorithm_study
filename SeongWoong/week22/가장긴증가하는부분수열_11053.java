import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // dp[i]는  숫자 arr[i]를 마지막원소로 하는 부분수열
        // 10 20 10 30 20 50
        // 기본값 1
        // dp[10] = 1
        // dp[x] = dp[x] 중에서 20보다 작은 x 중에서 가장 큰값 +1
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}
