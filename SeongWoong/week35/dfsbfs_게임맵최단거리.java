package 코딩테스트연습;

import java.util.PriorityQueue;
import java.util.Queue;

public class dfsbfs_게임맵최단거리 {
    public static void main(String[] args) {
        int[][] maps = new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
//        int[][] maps = new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
        Solution solution = new Solution();
        System.out.println(solution.solution(maps));
    }

    static class Solution {
        public int solution(int[][] maps) {
            int[] dy = new int[]{0, 0, 1, -1};
            int[] dx = new int[]{1, -1, 0, 0};
            boolean[][] visit = new boolean[maps.length][maps[0].length];
            int answer = -1;
            Queue<Node> q = new PriorityQueue<>();
            q.add(new Node(0, 0, 1));
            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (cur.y == maps.length - 1 && cur.x == maps[0].length - 1) {
                    answer = cur.cnt;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];
                    if (ny >= maps.length || ny < 0 || nx >= maps[0].length || nx < 0 || maps[ny][nx] == 0 || visit[ny][nx]) continue;
                    visit[ny][nx] = true;
                    q.add(new Node(ny, nx, cur.cnt + 1));
                }
            }
            return answer;
        }
    }

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }
}
