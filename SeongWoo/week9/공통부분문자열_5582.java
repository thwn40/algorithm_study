package SeongWoo.week9;

import java.util.Arrays;
import java.util.Scanner;

public class 공통부분문자열_5582 {
    public static void main(String[] args) {
        int[][] dp = new int[4001][4001];
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();

        //dp[i][j] = str1의 i번째 문자와 str2의 j번째 문자가 같을 때, str1과 str2에서 공통되고 연결되어있는 문자의 수
        for (int i = 0; i < str1.length(); i++) {
            char str1Word = str1.charAt(i);
            for (int j = 0; j < str2.length(); j++) {
                char str2Word = str2.charAt(j);
                if (str1Word == str2Word) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
            }
        }

        int longestSubString = Arrays.stream(dp)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();

        System.out.println(longestSubString);
    }
}
