package 코딩테스트연습;

import java.util.*;

public class 그래프_가장먼노드 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        Solution solution = new Solution();
        System.out.println(solution.solution(n, edge));
    }
    static class Solution {
        public int solution(int n, int[][] edge) {
            int answer = 0;
            boolean[] visit = new boolean[n+1];
            List<ArrayList<Integer>> links = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                links.add(new ArrayList<>());
            }
            for (int i = 0; i < edge.length; i++) {
                int from = edge[i][0];
                int to = edge[i][1];
                links.get(from).add(to);
                links.get(to).add(from);
            }
            Queue<Integer> q = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();
            q.add(1);
            visit[1] = true;
            while(!q.isEmpty()){    // 다음 노드로 이동하면서 큐에 넣어준다.
                int cnt = 0;    // 해당 단계에서 이동하는 카운트
                while(!q.isEmpty()){
                    int cur = q.poll();
                    for (int i = 0; i < links.get(cur).size(); i++) {
                        int next = links.get(cur).get(i);
                        if (!visit[next]) {
                            visit[next] = true;
                            q2.add(next);
                            cnt++;
                        }
                    }
                }
                q = q2;
                q2 = new LinkedList<>();
                if (cnt != 0) {
                    answer = cnt;
                }
            }
            return answer;
        }
    }
}
