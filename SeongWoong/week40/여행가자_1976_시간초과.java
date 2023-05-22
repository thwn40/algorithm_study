package SeongWoong.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_1976_시간초과 {
    static int N,M;
    static int[] plan;
    static boolean[][] city, visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        city = new boolean[N][N];
        plan = new int[M];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = st.nextToken().equals("1") ? true : false;
            }
        }
        String answer = "";
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken())-1;
        for (int i = 0; i < M-1; i++) {
            visit = new boolean[N][N];
            int to = Integer.parseInt(st.nextToken())-1;
            if (!dfs(from, to)) {
                answer = "NO";
                break;
            }
            from = to;
        }
        if (answer.equals("NO")) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }

    private static boolean dfs(int from, int to) {
        if (from == to) {
            return true;
        }
        for (int i = 0; i < N; i++) {
            if (city[from][i] && !visit[from][i]) {
                visit[from][i] = true;
                if (dfs(i, to)) {
                    return true;
                }
                visit[from][i] = false;
            }
        }
        return false;
    }
}
