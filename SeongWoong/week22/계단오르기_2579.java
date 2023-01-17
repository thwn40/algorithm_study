import java.util.Scanner;

public class 계단오르기_2579 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stair = new int[n + 1];
        int[][] sum = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            stair[i] = sc.nextInt();
        }
        sum[0][0] = 0;
        sum[0][1] = 0;
        sum[1][0] = stair[1];
        sum[1][1] = stair[1];
        for (int i = 2; i <= n; i++) {
            sum[i][1] = sum[i - 1][0] + stair[i];
            sum[i][0] = Math.max(sum[i - 2][1], sum[i - 2][0]) + stair[i];
        }

        System.out.println(Math.max(sum[n][0], sum[n][1]));
    }
}