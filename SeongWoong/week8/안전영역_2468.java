import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 안전영역_2468 {
    static int N;
    static int[][] all;
    static int[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 모든 영역
        all = new int[N][N];
        // 다른 높이들이 담긴 배열
        height = new int[101];
        // 가장 처음 변동이 있는 높이
        int min = 100;
        // 나눠진 구역 수
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int X = Integer.parseInt(st.nextToken());
                all[i][j] = X;
                height[X] = 1;
                min = Math.min(min, X);
            }
        }
        cnt = Math.max(cnt, pull(min));
        for (int i = min; i < height.length; i++) {
            if (height[i] != 0) {
                cnt = Math.max(cnt, pull(i));
            }
        }
        System.out.println(cnt);

    }

    // 해당 높이를 모두 침수시키는 함수
    public static int pull(int A) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (all[i][j] == A) all[i][j] = 0;
            }
        }
        return bfs();
    }

    // 구역 갯수 구하는 함수
    public static int bfs() {
        int[] dy = new int[]{1, -1, 0, 0};
        int[] dx = new int[]{0, 0, 1, -1};
        int result = 0;
        int[][] visited = new int[all.length][all.length];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (all[i][j] == 0 || visited[i][j] == 1) continue;
                visited[i][j] = 1;
                result++;
                q.add(new int[]{i, j});

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int cy = cur[0];
                    int cx = cur[1];
                    for (int k = 0; k < 4; k++) {
                        int ny = cy + dy[k];
                        int nx = cx + dx[k];
                        if (ny >= all.length || ny < 0 || nx >= all.length || nx < 0) continue;
                        if (visited[ny][nx] == 1) continue;
                        if (all[ny][nx] != 0) {
                            visited[ny][nx] = 1;
                            q.add(new int[]{ny, nx});
                        }
                    }

                }
            }
        }
        return result;
    }
}
