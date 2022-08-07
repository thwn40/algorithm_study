import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class 나이트의이동_7562 {
    static int T, des_y, des_x, I;
    static int[] dx, dy;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 8방향을 탐색
        dy = new int[]{1, -1, 1, -1, 2, 2, -2, -2};
        dx = new int[]{2, 2, -2, -2, 1, -1, 1, -1};
        // 테스트 케이스의 수
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            // 보드판의 행열
            I = Integer.parseInt(br.readLine());
            board = new int[I][I];

            String[] str = br.readLine().split(" ");
            // 시작위치 입력
            int cur_y = Integer.parseInt(str[0]);
            int cur_x = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            // 목적지 입력
            des_y = Integer.parseInt(str[0]);
            des_x = Integer.parseInt(str[1]);

            Queue<Integer[]> q = new LinkedList<>();
            q.add(new Integer[]{cur_y, cur_x});
            // 시작점은 방문했으니 체크
            board[cur_y][cur_x] = 1;

            while (!q.isEmpty()) {
                Integer[] arr = q.poll();
                // cy cx는 현재위치
                int cy = arr[0];
                int cx = arr[1];
                for (int j = 0; j < 8; j++) {
                    //ny nx는 다음칸
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];
                    if(cy==ny && cx==nx) break;
                    // 경계처리
                    if (ny < 0 || nx < 0 || ny >= I || nx >= I) continue;
                    // 방문한 적이 없다면
                    if (board[ny][nx] == 0) {
                        // 이동횟수 +1
                        board[ny][nx] = board[cy][cx] + 1;
                        // 그 칸에서 8방향 탐색을 위해 큐에 넣어줌
                        q.add(new Integer[]{ny, nx});
                    }
                }
            }
            System.out.println(board[des_y][des_x] - 1);
        }
    }
}
