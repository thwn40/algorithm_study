package 코딩테스트연습;

public class 동적계획법_등굣길 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 4;
        int n = 3;
        int[][] puddles = new int[][]{{2, 2}};
        System.out.println(solution.solution(m, n, puddles));
    }

    static class Solution {
        public int solution(int m, int n, int[][] puddles) {
            int[][] arr = new int[m + 1][n + 1];

            for (int i = 0; i < puddles.length; i++) {
                int y = puddles[i][0];
                int x = puddles[i][1];
                arr[y][x] = -1;
            }
            arr[1][1] = 1;
            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (arr[i][j] == -1) continue;
                    if (arr[i - 1][j] != -1) arr[i][j] += arr[i - 1][j] % 1000000007;
                    if (arr[i][j - 1] != -1) arr[i][j] += arr[i][j - 1] % 1000000007;
                }
            }
            return arr[m][n] % 1000000007;
        }
    }
}
