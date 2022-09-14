import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내리막길_1520 {
    static int[] dy, dx;
    static int M, N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        dy = new int[]{0, 0, 1, -1};
        dx = new int[]{1, -1, 0, 0};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        //세로
        M = Integer.parseInt(str[0]);
        //가로
        N = Integer.parseInt(str[1]);
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(run(0, 0));
    }

    static int run(int cy, int cx) {
        if (cy == M - 1 && cx == N - 1) return 1;

        dp[cy][cx] = 0;

        for (int i = 0; i < 4; i++) {
            int ny = cy + dy[i];
            int nx = cx + dx[i];

            if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
                if (map[cy][cx] > map[ny][nx]) {
                    if (dp[ny][nx] == -1) dp[cy][cx] += run(ny, nx);
                    else if (dp[ny][nx] >= 0) dp[cy][cx] += dp[ny][nx];
                }
            }
        }

        return dp[cy][cx];
    }
}
