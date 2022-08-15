import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7576 {
    static int[][] day;
    static int N, M;
    static Queue<Integer[]> q;

    public static void main(String[] args) throws IOException {
        //상하좌우 비교를 위한 방향
        final int[] dx = new int[]{-1, 0, 1, 0};
        final int[] dy = new int[]{0, -1, 0, 1};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N은 세로 M은 가로
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        // day 의 각 칸은 각 칸의 토마토가 익는데 걸리는 날짜+1
        day = new int[N][M];
        q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                day[i][j] = Integer.parseInt(st2.nextToken());
                // 이미 익어있는(값이 1 인) 좌표를 큐에 넣는다.
                if (day[i][j] == 1) {
                    q.add(new Integer[]{i, j});
                }
            }
        }
        // q가 빌 때까지 반복
        // (주변에 0인 토마토가 없을 때까지)
        while (!q.isEmpty()) {
            Integer[] A = q.poll();
            int y = A[0];
            int x = A[1];
            for (int i = 0; i < 4; i++) {
                if (find(y + dy[i], x + dx[i])) {
                    //4방향을 확인해서 0이 있으면 q에 해당좌표를 넣는다(1이 됐으므로)
                    q.add(new Integer[]{y + dy[i], x + dx[i]});
                    // 그리고 box의 해당 칸에 이전 칸의 수+1을 넣는다( 날짜표시 )
                    day[y + dy[i]][x + dx[i]] = day[y][x] + 1;
                }
            }

        }
        // 토마토가 채워진 날들을 비교해서 가장 큰날을 찾는다.(모두 채워진날)
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 아직 익지 않은 토마토가 있다면( 값이 0인 것이 있다면)
                // max를 0으로 초기화 시키고 중지
                if (day[i][j] == 0) {
                    max = 0;
                    break;
                }
                max = Math.max(max, day[i][j]);
            }
            // max가 0이라면 ( 중간에 익지 않은 토마토가 있어서 중지됐다면)
            // 더 이상 탐색할 필요가 없으므로 중지
            if (max == 0) break;
        }
        // 첫 토마토가 익은날은 포함하지 않으므로 -1을 해준다.
        System.out.println(max - 1);
    }

    // 해당 칸이 경계 안에 있고 0인지 확인
    public static boolean find(int y, int x) {
        if (y < 0 || y >= N || x < 0 || x >= M) return false;
        if (day[y][x] == 0) return true;
        return false;
    }
}
