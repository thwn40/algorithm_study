import java.util.Scanner;

public class 연속합_1912 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 주어지는 수의 개수
        int n = sc.nextInt();
        // dp[i] = i번째 수까지 합중에 최댓값
        int[] dp = new int[n];
        dp[0] = sc.nextInt();
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            // 직전dp가 음수면 그 전의 합은 버리고 처음부터 다시시작
            if (dp[i-1]<0) dp[i-1]=0;
            dp[i] = dp[i-1] + sc.nextInt();
            max = Math.max(max,dp[i]);
        }
        System.out.println(max);
    }
}