import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 벽부수고이동하기_2206_성공 {
    //3차원 배열로 벽이 깨졌을 때와 안깨졌을 때의 최소이동거리를 따로 저장하여 해결
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dy = new int[]{0, 0, 1, -1};
        int[] dx = new int[]{1, -1, 0, 0};
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        int[][] arr = new int[N][M];
        int[][][] answer = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
                answer[i][j][0] = 10000000;
                answer[i][j][1] = 10000000;
            }
        }
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, false));
        answer[0][0][0] = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                if (arr[ny][nx] == 1) {
                    if (cur.isBreak) {

                    } else {
                        if (answer[ny][nx][1] > answer[cur.y][cur.x][0] + 1){
                            answer[ny][nx][1] = answer[cur.y][cur.x][0] + 1;
                            q.add(new Node(ny, nx, true));
                        }
                    }
                } else {
                    if (cur.isBreak) {
                        if (answer[ny][nx][1] > answer[cur.y][cur.x][1] + 1){
                            answer[ny][nx][1] = answer[cur.y][cur.x][1] + 1;
                            q.add(new Node(ny, nx, true));
                        }
                    } else {
                        if (answer[ny][nx][0] > answer[cur.y][cur.x][0] + 1){
                            answer[ny][nx][0] = answer[cur.y][cur.x][0] + 1;
                            q.add(new Node(ny, nx, false));
                        }
                    }
                }
            }
        }
        if (answer[N - 1][M - 1][0] == 10000000 && answer[N - 1][M - 1][1] == 10000000) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(answer[N - 1][M - 1][0],answer[N - 1][M - 1][1]));
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
