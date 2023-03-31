import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 꼬리잡기놀이 {
    static int n, m, k, answer;
    static int[] dy, dx;
    static boolean[][][] visit;
    static Team[] teams;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = br.readLine().split(" ");
        n = Integer.parseInt(nmk[0]);
        m = Integer.parseInt(nmk[1]);
        k = Integer.parseInt(nmk[2]);
        graph = new int[n][n];
        dy = new int[]{1, 0, -1, 0};
        dx = new int[]{0, 1, 0, -1};
        teams = new Team[m];
        visit = new boolean[n][n][4];
        int teamNum = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    int[] yx = find(i, j);
                    teams[teamNum++] = new Team(i, j, yx[0], yx[1]);
                }
            }
        }
        for (int i = 0; i < k; i++) {
            walk();
            check(i);
        }
        System.out.println(answer);
    }

    private static int[] find(int i, int j) {
        int[] result = new int[]{i, j};
        if (graph[i][j] == 3) {
            return result;
        }
        for (int l = 0; l < 4; l++) {
            int ny = i + dy[l];
            int nx = j + dx[l];
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
            if (graph[ny][nx] == 2) {
                result = find(ny, nx);
            } else if (graph[ny][nx] == 3) {
                return new int[]{ny, nx};
            }
        }
        return result;
    }

    public static class Team {
        int startY;
        int startX;
        int endY;
        int endX;

        public Team(int startY, int startX, int endY, int endX) {
            this.startY = startY;
            this.startX = startX;
            this.endY = endY;
            this.endX = endX;
        }

        public void change() {
            int temp = startY;
            this.startY = this.endY;
            this.endY = temp;
            temp = startX;
            this.startX = this.endX;
            this.endX = temp;
        }
    }

    private static void check(int a) {
        int dir = a / n % n;
        int line = a % n;
        for (int i = 0; i < n; i++) {
            int ny = 0;
            int nx = 0;
            if (dir == 0) {
                ny = line;
                nx = i;
            } else if (dir == 1) {
                ny = n - 1 - i;
                nx = line;
            } else if (dir == 2) {
                ny = n - line - 1;
                nx = n - i - 1;
            } else if (dir == 3) {
                ny = i;
                nx = n - line - 1;
            }
            if (graph[ny][nx] == 1 || graph[ny][nx] == 3 || graph[ny][nx] == 2) {
                getScore(ny, nx, 1);
                break;
            }
        }
    }

    private static boolean getScore(int y, int x, int c) {
        if (graph[y][x] == 1) {
            answer += Math.pow(c,2);
            for (int i = 0; i < m; i++) {
                if (teams[i].startY == y && teams[i].startX == x) {
                    graph[y][x] = 3;
                    graph[teams[i].endY][teams[i].endX] = 1;
                    teams[i].change();
                    break;
                }
            }
            return true;
        }
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
            if (visit[ny][nx][i]==true) continue;
            if (graph[ny][nx] == 2 || graph[ny][nx] == 1) {
                visit[ny][nx][i] = true;
                if (getScore(ny, nx, c + 1)) {
                    return true;
                }
                visit[ny][nx][i] = false;
            }
        }
        return false;
    }

    private static void walk() {
        for (int i = 0; i < m; i++) {
            int startY = teams[i].startY;
            int startX = teams[i].startX;
            int endY = teams[i].endY;
            int endX = teams[i].endX;
            // 머리사람 옮기기
            graph[startY][startX] = 2;
            boolean fin = false;
            for (int j = 0; j < 4; j++) {
                int ny = startY + dy[j];
                int nx = startX + dx[j];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (graph[ny][nx] == 4) {
                    graph[ny][nx] = 1;
                    teams[i].startY = ny;
                    teams[i].startX = nx;
                    fin = true;
                    break;
                }
            }
            if (!fin) {
                graph[startY][startX] = 3;
                teams[i].endY = startY;
                teams[i].endX = startX;
                for (int j = 0; j < 4; j++) {
                    int ny = startY + dy[j];
                    int nx = startX + dx[j];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                    if (graph[ny][nx] == 2) {
                        graph[ny][nx] = 1;
                        teams[i].startY = ny;
                        teams[i].startX = nx;
                        break;
                    }
                }
                graph[endY][endX] = 2;
            } else {
                // 꼬리사람 옮기기
                graph[endY][endX] = 4;
                for (int j = 0; j < 4; j++) {
                    int ny = endY + dy[j];
                    int nx = endX + dx[j];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                    if (graph[ny][nx] == 2) {
                        graph[ny][nx] = 3;
                        teams[i].endY = ny;
                        teams[i].endX = nx;
                    }
                }
            }
        }
    }
}
