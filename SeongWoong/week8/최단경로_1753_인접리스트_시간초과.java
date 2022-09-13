import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 최단경로_1753_인접리스트_시간초과 {
    static int V, E, start, min;
    static boolean found;
    static int[] answer;
    static ArrayList<Map<Integer, Integer>>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        V = Integer.parseInt(str[0]);
        E = Integer.parseInt(str[1]);
        adjList = new ArrayList[V                                           + 1];
        start = Integer.parseInt(br.readLine());
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            String[] a = br.readLine().split(" ");
            int u = Integer.parseInt(a[0]);
            int v = Integer.parseInt(a[1]);
            int w = Integer.parseInt(a[2]);
            Map<Integer, Integer> map = new HashMap<>();
            map.put(v, w);
            adjList[u].add(map);
        }

        answer = new int[V + 1];
        Arrays.fill(answer, -1);
        for (int i = 1; i <= V; i++) {
            min = 3000000;
            found = false;

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

        for (int i = 0; i < adjList[sta].size(); i++) {
            Map<Integer, Integer> temp = adjList[sta].get(i);
            for (Map.Entry<Integer, Integer> elem : temp.entrySet()) {
                int key = elem.getKey();
                int val = elem.getValue();
                adjList[sta].remove(i);
                sum += val;
                if (elem.getKey() == des) {
                    min = Math.min(min, sum);
                    found = true;
                } else {
                    search(elem.getKey(), des, c + 1, sum);
                }
                sum -= elem.getValue();

                adjList[sta].add(temp);
            }
        }
    }
}
