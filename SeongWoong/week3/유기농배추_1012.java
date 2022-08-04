import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 유기농배추_1012 {
    static int T, N, M, K, cnt;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //테스트케이스개수
        T = Integer.parseInt(br.readLine());
        for (int c = 0; c < T; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            //세로길이
            N = Integer.parseInt(st.nextToken());
            //가로길이
            M = Integer.parseInt(st.nextToken());
            // 배추위치 개수
            K = Integer.parseInt(st.nextToken());

            graph = new int[N + 2][M + 2];

            for (int i = 0; i < K; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(st2.nextToken());
                int x = Integer.parseInt(st2.nextToken());
                graph[y + 1][x + 1] = 1;
            }
            //여기까지 입력 받아오기
            ArrayList<Integer> answer = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    //그래프에 값이 1이면 dfs실행
                    //이미 체크된 집들은 dfs내에서 1을 증가시켜줬기때문에 체크되지 않음
                    if (graph[i][j] == 1) {
                        cnt = 0;
                        dfs(i, j);
                        answer.add(cnt);
                    }
                }
            }
            System.out.println(answer.size());
        }
    }

    static void dfs(int y, int x) {
        //그래프 값이 1이 아니면 리턴
        if (graph[y][x] != 1) {
            return;
        } else if (graph[y][x] == 1) {
            //그래프값이 1이면 해당 값을 +1해줘서 체크했다는것을 표시하고 cnt+1
            graph[y][x]++;
            cnt++;
            //상하좌우로 1칸씩 이동하면서 체크
            dfs(y + 1, x);
            dfs(y - 1, x);
            dfs(y, x + 1);
            dfs(y, x - 1);
        }
    }

}