package SeongWoong.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_14503 {
    static int[][] room;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        N = Integer.parseInt(str.split(" ")[0]);
        M = Integer.parseInt(str.split(" ")[1]);
        room = new int[N][M];
        str = br.readLine();

        int currentY = Integer.parseInt(str.split(" ")[0]);
        int currentX = Integer.parseInt(str.split(" ")[1]);
        int d = Integer.parseInt(str.split(" ")[2]);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        run(currentY, currentX, d);

    }

    public static void run(int y, int x, int d) {
        // 1현재칸을 청소
        if (room[y][x] == 0) room[y][x] = 2;
        // 2왼쪽부터 체크
        while (canSweep(y, x)) {
            int ly = left(y, x, d)[0];
            int lx = left(y, x, d)[1];

            // 3 왼쪽이 청소가 안됐다면 왼쪽으로 회전 후 한칸전진 후 1번반복
            if (room[ly][lx] == 0) run(ly, lx, rotate(d));
            // 4 왼쪽이 청소가 됐다면 왼쪽으로 회전 후 2번반복
            if (room[ly][lx] == 2 || room[ly][lx] == 1) d = rotate(d);
        }

        int by = back(y, x, d)[0];
        int bx = back(y, x, d)[1];
        if (room[by][bx] != 1) {
            run(by, bx, d);
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (room[i][j] == 2) cnt++;
            }
        }
        System.out.println(cnt);
        System.exit(0);
        // 5 네방향 모두 청소가 됐다면 회전하지않고 한칸 후진 후 2번반복
        // 6 네방향 모두 청소가 됐고 뒤가 벽이라 후진이 불가한 경우 중지
    }

    public static int rotate(int d) {
        if (d == 0) return 3;
        return d - 1;
    }

    public static int[] left(int y, int x, int d) {
        if (d == 0) return new int[]{y, x - 1};
        if (d == 1) return new int[]{y - 1, x};
        if (d == 2) return new int[]{y, x + 1};
        else return new int[]{y + 1, x};
    }

    public static int[] back(int y, int x, int d) {
        if (d == 0) return new int[]{y + 1, x};
        if (d == 1) return new int[]{y, x - 1};
        if (d == 2) return new int[]{y - 1, x};
        else return new int[]{y, x + 1};
    }

    public static boolean canSweep(int y, int x) {
        int[] dy = new int[]{0, 0, 1, -1};
        int[] dx = new int[]{1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            if (room[y + dy[i]][x + dx[i]] == 0) return true;
        }
        return false;
    }
}
