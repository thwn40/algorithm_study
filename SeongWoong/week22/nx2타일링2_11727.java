import java.util.Scanner;

public class nx2타일링2_11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n>1) {
            dp[2] = 3;
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007;
            }
        }
        System.out.println(dp[n]);
    }
}
