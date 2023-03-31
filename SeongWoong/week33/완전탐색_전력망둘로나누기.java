import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 완전탐색_전력망둘로나누기 {
    public static void main(String[] args){
        Solution solution = new Solution();
        int n = 9;
        int[][] wires = new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        System.out.println(solution.solution(9, wires));
    }
    static class Solution {
        static int ans; // 답
        static List<Integer>[] list;    // 각 노드에 연결된 다른 노드 개수
        public int solution(int n, int[][] wires) {
            ans = Integer.MAX_VALUE;    // 최소값을 구해야하므로 초기값을 최대로
            list = new ArrayList[101];  // 송전탑의 개수는 최대 100개
            for (int i = 0; i < 101; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < wires.length; i++) {
                int v1 = wires[i][0];
                int v2 = wires[i][1];
                list[v1].add(v2);   // 양방향 그래프이므로 둘다 넣어줌
                list[v2].add(v1);
            }
            for (int i = 0; i < wires.length; i++) {
                int v1 = wires[i][0];
                int v2 = wires[i][1];
                cut(v1,v2);
            }
            return ans;
        }
        public void cut(int v1, int v2){
            int cnt1 = count(v1,v2);    // v1에서부터 연결된 노드의 개수
            int cnt2 = count(v2,v1);    // v2에서부터 연결된 노드의 개수
            ans = Math.min(ans, Math.abs(cnt1 - cnt2)); // 갱신
        }

        public int count(int v1,int v2) {
            int cnt = 1;
            Queue<Integer> q = new LinkedList<>();
            q.add(v1);
            boolean[] visited = new boolean[list.length];
            visited[v1] = true; // 중복방지를 위한 배열
            while(!q.isEmpty()){
                int cur = q.poll(); // 현재 노드
                for (int i = 0; i < list[cur].size(); i++) {
                    int next = list[cur].get(i);    // 연결된 노드를 다시 큐에 넣으면서 개수구하기
                    if (visited[next] || next==v2) continue;    // v2는 끊어졌으므로 넘어가기
                    visited[next] = true;
                    q.add(list[cur].get(i));
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
