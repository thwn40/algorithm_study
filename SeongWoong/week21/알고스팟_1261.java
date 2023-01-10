import java.util.*;

public class 알고스팟_1261 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        sc.nextLine();
        int[] dy = new int[]{0, 0, 1, -1};
        int[] dx = new int[]{1, -1, 0, 0};

        int[][] maze = new int[N][M];
        // answer 의 값은 해당 방으로 이동하기 위해 부숴야하는 벽의 갯수
        int[][] answer = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = str.charAt(j) - '0';
                answer[i][j] = 100000000;
            }
        }
        Queue<room> q = new LinkedList<>();
        q.add(new room(0, 0));
        answer[0][0] = 0;
        while (!q.isEmpty()) {
            room cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny >= N || nx >= M || ny < 0 || nx < 0) continue;
                if (maze[ny][nx] == 0) {
                    if (answer[ny][nx] > answer[cur.y][cur.x]) {
                        answer[ny][nx] = answer[cur.y][cur.x];
                        q.add(new room(ny, nx));
                    }
                } else {
                    if (answer[ny][nx] > answer[cur.y][cur.x] + 1) {
                        answer[ny][nx] = answer[cur.y][cur.x] + 1;
                        q.add(new room(ny, nx));
                    }
                }
            }
        }
        System.out.println(answer[N - 1][M - 1]);
    }

    public static class room {
        int y;
        int x;

        public room(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
