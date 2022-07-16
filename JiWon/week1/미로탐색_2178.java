package JiWon.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탐색_2178 {
    static int[][] board;
    static int n;
    static int m;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }
        visited = new boolean[n][m];
        visited[0][0] = true;
        bfs(0, 0);
        System.out.println(board[n - 1][m - 1]);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int tx = now[0];    //x 대입
            int ty = now[1];    //y
            for (int i = 0; i < 4; i++) {   //현재 위치에 다음 위치 대입
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m)     //보드 범위에서 벗어나면 아무것도 안하고 넘어가세유
                    continue;
                if (visited[nx][ny] || board[nx][ny] == 0)  //방문한 적 있거나 보드가 0이면 넘어가삼유
                    continue;
                //next actions
                q.add(new int[]{nx, ny});   //else 현재 위치 좌표 큐에 넣긔
                board[nx][ny] = board[tx][ty] + 1;  //보드 현재 위치에 이전 위치 + 1 = 카운트++
                visited[nx][ny] = true;     //방문 체크
            }
        }
    }
}
