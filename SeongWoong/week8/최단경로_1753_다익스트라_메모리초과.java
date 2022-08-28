import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 최단경로_1753_다익스트라_메모리초과 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int V = Integer.parseInt(str[0]);
        int E = Integer.parseInt(str[1]);
        int[][] all = new int[V + 1][V + 1];
        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            String[] a = br.readLine().split(" ");
            int u = Integer.parseInt(a[0]);
            int v = Integer.parseInt(a[1]);
            int w = Integer.parseInt(a[2]);
            if (all[u][v] == 0) {
                all[u][v] = w;
//                all[v][u] = w;
            }
            all[u][v] = Math.min(all[u][v], w);
//            all[v][u] = Math.min(all[u][v], w);

        }
        int[] startArr = new int[V + 1];
        ArrayList<Integer> idxList = new ArrayList<>();
        for (int i = 1; i <= V ; i++) {
            startArr[i] = all[start][i];
//            if (startArr[i]==0) startArr[i] = Integer.MAX_VALUE;
            if (all[start][i]!=0) idxList.add(i);
        }
        boolean[] visited = new boolean[V + 1];
        visited[start] = true;

        for (int i = 0; i < idxList.size(); i++) {
            int idx = 1;
            long min = Integer.MAX_VALUE;
            for (int j = 0; j < idxList.size() ; j++) {
                if (visited[idxList.get(j)]) continue;
                if (min>startArr[idxList.get(j)]) {
                    min = startArr[idxList.get(j)];
                    idx = idxList.get(j);
                }
            }
            visited[idx] = true;
            for (int j = 1; j <= V; j++) {
                if (all[idx][j]==0) continue;
                if (startArr[j]==0) startArr[j] = startArr[idx] + all[idx][j];
                startArr[j] = Math.min(startArr[j], startArr[idx] + all[idx][j]);
            }
        }
        for (int i = 1; i <= V; i++) {
            if (i == start) System.out.println(0);
            else if (startArr[i] == 0) System.out.println("INF");
            else System.out.println(startArr[i]);
        }
    }
}
