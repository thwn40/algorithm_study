import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 예술성 {
    static int[] dy, dx, cntOfGroup;
    static int n, answer;
    static int[][] graph, group, pair;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dy = new int[]{-1, 0, 1, 0};
        dx = new int[]{0, -1, 0, 1};
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
        check();
        System.out.println(answer);
        spin();
        check();
        System.out.println(answer);
        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(group[i]));
            System.out.println(Arrays.toString(graph[i]));
//            System.out.println(Arrays.toString(pair[i]));
        }
        spin();
        check();
        System.out.println(answer);
        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(group[i]));
            System.out.println(Arrays.toString(graph[i]));
//            System.out.println(Arrays.toString(pair[i]));
        }
        spin();
        check();
        System.out.println(answer);
//        System.out.println(Arrays.toString(cntOfGroup));
        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(group[i]));
            System.out.println(Arrays.toString(graph[i]));
//            System.out.println(Arrays.toString(pair[i]));
        }
    }

    private static void check() {
        // <a,b> a번 그룹을 이루는 숫자는 b
        Map<Integer, Integer> map = new HashMap<>();
        // 그룹화 하기
        group = new int[n][n];
        cntOfGroup = new int[n * n + 1];
        int c = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (group[i][j] == 0) {
                    map.put(c, graph[i][j]);
                    group[i][j] = c;
                    cntOfGroup[c]++;
                    dfs(i, j, c);
                    c++;
                }
            }
        }
        pair = new int[c][c];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                checkPair(i, j);
            }
        }
        for (int i = 1; i < c; i++) {
            for (int j = i; j < c; j++) {
                if (pair[i][j] != 0) {
                    int group1 = map.get(i);
                    int group2 = map.get(j);

                    answer += (cntOfGroup[i] + cntOfGroup[j]) * group1 * group2 * pair[i][j];
                }
            }
        }
    }

    // y번 그룹과 x번 그룹의 변이 몇 개 붙어있나 확인하는 메서드
    private static void checkPair(int y, int x) {
        int cur = group[y][x];
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                continue;
            }
            int next = group[ny][nx];
            if (cur != next) {
                pair[cur][next]++;
            }
        }
    }

    //구역 나누기
    public static void dfs(int y, int x, int c) {
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                continue;
            }
            if (graph[y][x] == graph[ny][nx]) {
                if (group[ny][nx] == 0) {
                    group[ny][nx] = c;
                    cntOfGroup[c]++;
                    dfs(ny, nx, c);
                }
            }
        }
    }
    // 돌리기
    // 돌리는데만 한시간 ㅋㅋ
    private static void spin() {
        //십자돌리기
        int mid = graph.length / 2;
        int[][] remake = new int[n][n];

        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            remake[i][mid] = graph[mid][n - 1 - i];
            remake[mid][i] = graph[i][mid];
        }

        // 나머지 돌리기
        int len = mid;

        // 좌상단
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                remake[i][j] = graph[mid - j - 1][i];
            }
        }
        // 우상단
        for (int i = 0; i < mid; i++) {
            for (int j = mid + 1; j < n; j++) {
                remake[i][j] = graph[n - j - 1][mid + 1 + i];
            }
        }
        // 좌하단
        for (int i = mid + 1; i < n; i++) {
            for (int j = 0; j < mid ; j++) {
                remake[i][j] = graph[n - j - 1][i - mid - 1];
            }
        }
        // 우하단
        for (int i = mid + 1; i < n; i++) {
            for (int j = mid + 1; j < n; j++) {
                remake[i][j] = graph[n-j+mid][i];
            }
        }
        graph = remake;
    }
}
