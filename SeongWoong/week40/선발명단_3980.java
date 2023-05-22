package SeongWoong.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 선발명단_3980 {
    static int[] position;
    static int[][] ability;
    static int C, answer;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        for (int r = 0; r < C; r++) {
            answer = 0;
            position = new int[11];
            ability = new int[11][11];
            used = new boolean[11];
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    ability[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0);
            System.out.println(answer);
        }
    }

    private static void dfs(int cur, int sum) {
        if (cur == 11) {
            answer = Math.max(answer, sum);
        }
        for (int i = 0; i < 11; i++) {
            if (!used[i] && ability[i][cur] != 0) {
                used[i] = true;
                dfs(cur + 1, sum + ability[i][cur]);
                used[i] = false;
            }
        }
    }
}
