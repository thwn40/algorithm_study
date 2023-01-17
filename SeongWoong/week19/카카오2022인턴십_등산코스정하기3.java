import java.util.*;

public class 카카오2022인턴십_등산코스정하기3 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int[][] paths = new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = new int[]{1, 3};
        int[] summits = new int[]{5};
        System.out.println(Arrays.toString(sol.solution(n, paths, gates, summits)));
    }

    static class Solution {
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            int summit = 0;
            int intensity = 0;

            for (int i = 0; i < gates.length; i++) {
                for (int j = 0; j < summits.length; j++) {
                    int from = gates[i];
                    int to = summits[j];
                    int temp = dfs(paths, from, to);
                }
            }
            return new int[]{summit, intensity};
        }
        public int dfs(int[][] paths, int from, int to){
            //
            return 1;
        }
    }
}
