import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 단지번호붙이기_2667 {
    static int N, cnt;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= N; j++) {
                graph[i][j] = str.charAt(j - 1) - '0';
            }
        }
        //여기까지 입력 받아오기
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
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
        //오름 차순으로 정렬
        Collections.sort(answer);
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }

    static void dfs(int x, int y) {
        //그래프 값이 1이 아니면 리턴
        if (graph[x][y] != 1) {
            return;
        } else if (graph[x][y] == 1) {
            //그래프값이 1이면 해당 값을 +1해줘서 체크했다는것을 표시하고 cnt+1
            graph[x][y]++;
            cnt++;
            //상하좌우로 1칸씩 이동하면서 체크
            dfs(x + 1, y);
            dfs(x - 1, y);
            dfs(x, y + 1);
            dfs(x, y - 1);
        }
    }
}