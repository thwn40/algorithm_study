import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무박멸 {
    static int n, m, k, c, answer;
    static int[] dy, dx;
    static Field[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmkc = br.readLine().split(" ");
        n = Integer.parseInt(nmkc[0]);  // 그래프 크기
        m = Integer.parseInt(nmkc[1]);  // 진행년수
        k = Integer.parseInt(nmkc[2]);  // 제초제 범위
        c = Integer.parseInt(nmkc[3]) + 1;  // 제초제년수
        dy = new int[]{1, 0, 0, -1};
        dx = new int[]{0, 1, -1, 0};
        graph = new Field[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = new Field(Integer.parseInt(st.nextToken()), 0);
            }
        }
        for (int i = 0; i < m; i++) {
            grow();     //성장
            breed();    // 번식
            weed();     // 제초제
        }
        System.out.println(answer);
    }

    public static class Field {
        int num;    // 해당 숫자
        int hrb;    // 제초제 년수
        public Field(int num, int hrb) {
            this.num = num;
            this.hrb = hrb;
        }
    }

    private static void weed() {
        int maxSum = 0;
        int maxY = 0;
        int maxX = 0;
        int[] my = new int[]{1, -1, 1, -1};
        int[] mx = new int[]{1, 1, -1, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = graph[i][j].num;
                if (graph[i][j].num == 0) {

                } else {
                    if (graph[i][j].hrb != 0 || graph[i][j].num == -1) continue;
                    for (int t = 0; t < 4; t++) {   // 4방향으로
                        for (int l = 1; l <= k; l++) {  //l만큼 제초제 뿌리기
                            int ny = i + my[t] * l;
                            int nx = j + mx[t] * l;
                            if (ny >= n || nx >= n || ny < 0 || nx < 0) break;
                            if (graph[ny][nx].num > 0) {
                                sum += graph[ny][nx].num;
                            }
                            if (graph[ny][nx].num == -1 || graph[ny][nx].num == 0) break;
                        }
                    }
                }
                if (sum > maxSum) {
                    maxY = i;
                    maxX = j;
                    maxSum = sum;
                }
            }
        }
        graph[maxY][maxX].num = 0;      //확정된 좌표에 살포!
        graph[maxY][maxX].hrb = c;      //확정된 좌표에 살포!

        for (int t = 0; t < 4; t++) {
            for (int l = 1; l <= k; l++) {
                int ny = maxY + my[t] * l;
                int nx = maxX + mx[t] * l;
                if (ny >= n || nx >= n || ny < 0 || nx < 0) break;
                int temp = graph[ny][nx].num;
                if (graph[ny][nx].num == -1) break;
                if (graph[ny][nx].num >= 0) {
                    graph[ny][nx].num = 0;
                    graph[ny][nx].hrb = c;
                }
                if (temp == 0) break;
            }
        }
        answer += maxSum;
    }

    private static void grow() {    // 성장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j].hrb != 0) {
                    graph[i][j].hrb--;
                }
                if (graph[i][j].num != 0 && graph[i][j].num != -1) {
                    for (int l = 0; l < 4; l++) {
                        int ny = i + dy[l];
                        int nx = j + dx[l];
                        if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                            continue;
                        }
                        if (graph[ny][nx].num != 0 && graph[ny][nx].num != -1) {
                            graph[i][j].num++;
                        }
                    }
                }
            }
        }
    }

    private static void breed() {   //성장
        Field[][] grown = new Field[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grown[i][j] = new Field(graph[i][j].num, graph[i][j].hrb);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j].num == 0 || graph[i][j].num == -1) {

                } else {
                    int cnt = 0;
                    for (int l = 0; l < 4; l++) {   //4 방향으로 가능한 갯수 체크해서
                        int ny = i + dy[l];
                        int nx = j + dx[l];
                        if (ny < 0 || ny >= n || nx < 0 || nx >= n || graph[ny][nx].num != 0 || graph[ny][nx].hrb != 0) {
                            continue;
                        }
                        cnt++;
                    }
                    for (int l = 0; l < 4; l++) {   // 가능한 갯수만큼 증가
                        int ny = i + dy[l];
                        int nx = j + dx[l];
                        if (ny < 0 || ny >= n || nx < 0 || nx >= n || graph[ny][nx].num != 0 || graph[ny][nx].hrb != 0) {
                            continue;
                        }
                        grown[ny][nx].num += graph[i][j].num / cnt;
                    }
                }
            }
        }
        graph = grown;
    }
}
