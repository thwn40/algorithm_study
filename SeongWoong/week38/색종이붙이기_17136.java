import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기_17136 {
    static int answer;
    static int[] cnts;
    static int[][] all;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        all = new int[10][10];
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                all[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cnts = new int[6];
        answer = -1;
        run(0, 0, 0);
        System.out.println(answer);
    }

    public static void run(int y, int x, int cnt) {
        if (x == 10) {
            run(y + 1, 0, cnt);
            return;
        }

        if (y == 10) {
            if (answer == -1) answer = cnt;
            answer = Math.min(answer, cnt);
            return;
        }

        if (all[y][x] == 1) {
            for (int i = 1; i <= 5; i++) {
                if (can(y, x, i)) {
                    cnts[i]++;
                    change(y, x, i);
                    run(y, x + 1, cnt + 1);
                    change(y, x, i);
                    cnts[i]--;
                }
            }
        } else {
            run(y, x + 1, cnt);
        }
    }

    public static boolean can(int y, int x, int len) {
        if (cnts[len] == 5) return false;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (y + i >= 10 || x + j >= 10) return false;
                if (all[y + i][x + j] != 1) return false;
            }
        }
        return true;
    }

    public static void change(int y, int x, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                all[y + i][x + j] = all[y + i][x + j] == 1 ? 0 : 1;
            }
        }
    }
}
