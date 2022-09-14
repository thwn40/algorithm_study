import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 최단경로_1753_인접행렬_메모리초과 {
    static int V, E, start, min;
    static boolean found;
    static int[] answer;
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        V = Integer.parseInt(str[0]);
        E = Integer.parseInt(str[1]);
        graph = new int[V + 1][V + 1];
        start = Integer.parseInt(br.readLine());
        for (int i = 0; i < E; i++) {
            String[] a = br.readLine().split(" ");
            int u = Integer.parseInt(a[0]);
            int v = Integer.parseInt(a[1]);
            int w = Integer.parseInt(a[2]);

            graph[u][v] = w;
        }
        answer = new int[V + 1];
        Arrays.fill(answer, -1);
        for (int i = 1; i <= V; i++) {
            min = 3000000;
            found = false;
            visited = new boolean[V + 1][V + 1];

            if (i == start) {
                answer[i] = 0;
                found = true;
            } else {
                search(start, i, 0, 0);
                answer[i] = min;
            }
            if (found == false) answer[i] = -1;
        }

        for (int i = 1; i <= V; i++) {
            if (answer[i] == -1) System.out.println("INF");
            else System.out.println(answer[i]);
        }
    }

    public static void search(int sta, int des, int c, int sum) {

        if (c == E) return;

        for (int i = 1; i <= V; i++) {
            if (graph[sta][i] != 0 && visited[sta][i] == false) {
                visited[sta][i] = true;
                sum += graph[sta][i];
                if (i == des) {
                    min = Math.min(min, sum);
                    found = true;
                } else {
                    search(i, des, c + 1, sum);
                }
                sum -= graph[sta][i];
                visited[sta][i] = false;
            }
        }
    }
}
