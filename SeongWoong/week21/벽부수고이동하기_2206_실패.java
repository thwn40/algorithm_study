import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 벽부수고이동하기_2206_실패 {
    // 21퍼에서 실패
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dy = new int[]{0, 0, 1, -1};
        int[] dx = new int[]{1, -1, 0, 0};
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        int[][] arr = new int[N][M];
        int[][] answer = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
                answer[i][j] = 1000000;
            }
        }
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, false));
        answer[0][0] = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (arr[ny][nx] == 1) {
                    if (cur.isBreak) continue;
                    if (answer[ny][nx] > answer[cur.y][cur.x] + 1) {
                        answer[ny][nx] = answer[cur.y][cur.x] + 1;
                        q.add(new Node(ny, nx, true));
                    }
                } else {
                    if (answer[ny][nx] > answer[cur.y][cur.x] + 1) {
                        answer[ny][nx] = answer[cur.y][cur.x] + 1;
                        q.add(new Node(ny, nx, cur.isBreak));
                    }
                }
            }
        }
        if (answer[N - 1][M - 1] == 1000000) {
            System.out.println(-1);
        } else {
            System.out.println(answer[N - 1][M - 1]);
        }
    }

    static class Node {
        int y;
        int x;
        boolean isBreak;

        public Node(int y, int x, boolean isBreak) {
            this.y = y;
            this.x = x;
            this.isBreak = isBreak;
        }
    }
}
