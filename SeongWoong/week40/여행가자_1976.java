package SeongWoong.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_1976 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] route = new int[N][N];
        String answer = "YES";
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                route[i][j] = Integer.parseInt(st.nextToken());
                if (i == j) {   // 엣지케이스 같은 곳으로 가는 것도 체크해야함
                    route[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {   // 플로이드 와샬 알고리즘
            for (int j = 0; j < N; j++) {   // 3중 포문을 돌리면 모든 경로를 계산할 수 있다.
                for (int k = 0; k < N; k++) {   // 도시가 200 시간복잡도는 200^3
                    if (route[j][i] == 1 && route[i][k] == 1) {
                        route[j][k] = 1;
                    }
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int from  = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < M-1; i++) {
            int to = Integer.parseInt(st.nextToken()) - 1;
            if (route[from][to] != 1) {
                answer = "NO";
                break;
            }
        }
        System.out.println(answer);
    }
}
