import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미세먼지안녕_17144 {
    static int[][] room;
    static int[] dy, dx;
    static int r, c, t, machineTop, machineUnder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dy = new int[]{0, 0, 1, -1};
        dx = new int[]{1, -1, 0, 0};
        r = Integer.parseInt(st.nextToken());   // 행
        c = Integer.parseInt(st.nextToken());   //  열
        t = Integer.parseInt(st.nextToken());   // 시간
        room = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    machineTop = i;
                }
            }
        }
        for (int i = 0; i < t; i++) {
            spread();
            run(machineTop);
        }
        int sum = 2;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                sum += room[i][j];
            }
        }
        System.out.println(sum);
    }

    public static void spread() {   // 확산
        int[][] temp = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (room[i][j] != 0) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (ny == 0 || nx == 0 || ny > r || nx > c) continue;   // 범위 벗어남
                        if (room[ny][nx] == -1) continue;   // 공기청정기 넘어감
                        temp[ny][nx] += room[i][j] / 5;
                        cnt++;
                    }
                    temp[i][j] += room[i][j] - (room[i][j] / 5 * cnt);
                }
            }
        }
        room = temp;
    }

    public static void run(int machineTop) {    // 공기청정기 작동
        for (int i = 2; i < machineTop - 1; i++) {
            room[machineTop - i][1] = room[machineTop - i - 1][1];
        }
        for (int i = 1; i < c; i++) {
            room[1][i] = room[1][i + 1];
        }
        for (int i = 1; i < machineTop - 1; i++) {
            room[i][c] = room[i + 1][c];
        }
        for (int i = 0; i < c-1; i++) {
            room[machineTop-1][c-i] = room[machineTop-1][c-i-1];
        }
        room[machineTop-1][2] = 0;

        int machineUnder = machineTop + 1;
        for (int i = machineUnder; i < r; i++) {
            room[i][1] = room[i + 1][1];
        }
        for (int i = 1; i < c; i++) {
            room[r][i] = room[r][i+1];
        }
        for (int i = 0; i < r-machineTop; i++) {
            room[r - i][c] = room[r - i - 1][c];
        }
        for (int i = 0; i < c-1; i++) {
            room[machineUnder-1][c-i] = room[machineUnder-1][c-i-1];
        }
        room[machineUnder-1][2] = 0;
    }
}
