import java.util.Scanner;

public class 포도주시식_2156 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        int[] all = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            all[i] = sc.nextInt();
        }
        dp[1] = all[1];
        for (int i = 2; i <= n; i++) {
            if(i==2){
                dp[2] = all[1] + all[2];
                continue;
            }
            if (i==3){
                dp[3] = Math.max(dp[2], Math.max(dp[1] + all[3], all[2] + all[3]));
                continue;
            }
            dp[i] = Math.max(dp[i - 3] + all[i - 1] + all[i], Math.max(dp[i - 1], dp[i - 2] + all[i]));
        }
        System.out.println(dp[n]);
    }
}
// 10 1 13 2 8 1

//dp[2]
// O O = 16

//dp[3]
// O O X dp[2] = 16
// O X O dp[1] + all[3] = 19
// X O O all[2] + all[3] = 23

// O O X O dp[i-2] + all[i] = 25
// O X O O dp[i-3] + all[i-1] + all[i] = 28
// X O O X dp[i-1] = 23

// O O X O O dp[i-3] + all[i-1] + all[i] = 33
// O X O O X dp[i-1] = 28
// O X O X O dp[i-2] 에서 아래것만 선택됨
// X O O X O dp[i-2] + all[i] = 31

