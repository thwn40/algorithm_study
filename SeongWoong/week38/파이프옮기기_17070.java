package SeongWoong.week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기_17070 {
    static int N, answer;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 1, 1);
        System.out.println(answer);
    }

    public static void dfs(int y, int x, int dir) {    // dir 1:가로 2: 세로 3: 대각선
        if (y == N - 1 && x == N - 1) {
            answer++;
        }

        int ny, nx;
        if (dir == 1) { // 현재 가로인 경우
            // 가로 이동
            move(y, x + 1, 1);
        } else if (dir == 2) {  // 현재 세로인 경우
            // 세로 이동
            move(y + 1, x, 2);
        } else {    // 현재 대각선인 경우
            // 가로 이동
            move(y, x + 1, 1);
            // 세로 이동
            move(y + 1, x, 2);
        }
        // 대각선 이동
        move(y + 1, x + 1, 3);
    }

    public static void move(int ny, int nx, int dir) {
        if (ny < N && nx < N && arr[ny][nx] != 1) {
            if (dir == 3) {
                if (arr[ny - 1][nx] == 1 || arr[ny][nx - 1] == 1) return;
            }
            dfs(ny, nx, dir);
        }
    }
}
