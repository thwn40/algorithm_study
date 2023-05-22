package SeongWoong.week35;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class dfsbfs_네트워크 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.solution(n, computers));
    }
    static class Solution {
        public int solution(int n, int[][] computers) {
            int cnt = 0;
            List<Integer>[] list = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                list[i] = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if (computers[i][j] == 1) {
                        list[i].add(j);
                    }
                }
            }
            boolean[] checked = new boolean[n];
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (checked[i]) continue;
                q.add(i);
                cnt++;
                while (!q.isEmpty()) {
                    int cur = q.poll();

                    checked[cur] =true;
                    for (int next : list[cur]) {
                        if (checked[next]) continue;
                        checked[next] = true;
                        q.add(next);
                    }
                }
            }
            return cnt;
        }
    }
}
