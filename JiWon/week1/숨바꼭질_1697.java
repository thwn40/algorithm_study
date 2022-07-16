package JiWon.week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질_1697 {
    static int[] visited = new int[10001];
    static int[] move = {-1, 1, 2};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        System.out.println(BFS(n, k));
    }

    public static int BFS(int n, int k) {
        Queue<Integer> q = new LinkedList();
        visited[n] = 1;
        q.offer(n);
        int level = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int x = q.poll();
                for (int j = 0; j < 3; j++) {
                    int nx;
                    if (move[j] == 2) {
                        nx = x * move[j];
                    } else {
                        nx = x + move[j];
                    }
                    if (nx == k) {
                        return level + 1;
                    }
                    if (nx > 0 && nx <= 10000 && visited[nx] == 0) {
                        visited[nx] = 1;
                        q.offer(nx);
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
