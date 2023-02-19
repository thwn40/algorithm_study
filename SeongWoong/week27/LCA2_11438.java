package SeongWoong.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LCA2_11438 {
    static int N, K;
    static int[] level;
    static int[][] parent;
    static List<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        // K는 level을 2의 n승으로 나눴을 때 가능한 최대 n
        K = 0;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }
        for (int i = 1; i < N; i++) {
            String[] str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0]);
            int to = Integer.parseInt(str[1]);
            arr[from].add(to);
            arr[to].add(from);
        }

        level = new int[N + 1];
        parent = new int[N + 1][K + 1];

        level[1] = 0;

        // 바로 상위 부모 찾기
        dfs(1, 1);

        // 그보다 상위 부모 채우기 (parent 전부 채우기)
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }

        // 탐색 시작
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);

            // 깊이가 낮은 쪽을 기준으로 변경하기
            if (level[a] < level[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            // 불필요한 과정을 줄이기 위해
            for (int j = K - 1; j >= 0; j--) {
                if (Math.pow(2, j) <= level[a] - level[b]) {
                    a = parent[a][j]; // a를 2^j 번 째 조상 노드로 대체한다.
                }
            }

            if (a == b) { // a와 b가 같으면 공통조상 찾은 것
                System.out.println(a);
            } else { // 다르면
                for (int j = K - 1; j >= 0; j--) {
                    if (parent[a][j] != parent[b][j]) { // a와 b를 2^j 번 째 조상 노드로 대체한다.
                        a = parent[a][j];
                        b = parent[b][j];
                    }
                }
                // 마지막 a b는 초기 a,b의 공통조상노드에서 1번째 아래에있는 노드 들이기 때문에
                // 원하는 값은 바로 상위 조상노드를 출력하면된다
                System.out.println(parent[a][0]);
            }
        }
    }

    private static void dfs(int node, int cnt) {
        level[node] = cnt; // node의 깊이 저장
        for (int n : arr[node]) { // node와 연결된 곳 중
            if (level[n] != 0) continue; // 방문한 곳은 패스
            parent[n][0] = node; // n의 첫 번째 부모는 node
            dfs(n, cnt + 1); // n으로 이동
        }
    }
}
