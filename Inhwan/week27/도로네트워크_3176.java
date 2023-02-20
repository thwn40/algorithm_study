package Inhwan.week27;

import java.io.*;
import java.util.*;

public class 도로네트워크_3176 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int l = (int) (Math.log10(n) / Math.log10(2)) + 1;

        int[][] parent = new int[n + 1][];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = new int[l + 1];
        }

        int[] depth = new int[n + 1];

        int[][] mins = new int[n + 1][];
        int[][] maxes = new int[n + 1][];
        for (int i = 0; i < n + 1; i++) {
            mins[i] = new int[l + 1];
            maxes[i] = new int[l + 1];
        }

        List<Edge>[] adj = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int v1 = edge[0], v2 = edge[1], distance = edge[2];

            adj[v1].add(new Edge(v2, distance));
            adj[v2].add(new Edge(v1, distance));
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (Edge e : adj[v]) {
                int u = e.to;

                if (u > 1 && depth[u] == 0) {
                    q.add(u);

                    depth[u] = depth[v] + 1;
                    parent[u][0] = v;
                    mins[u][0] = e.distance;
                    maxes[u][0] = e.distance;

                    int i = 1;

                    while (true) {
                        parent[u][i] = parent[parent[u][i - 1]][i - 1];
                        if (parent[u][i] == 0) break;

                        mins[u][i] = Math.min(mins[u][i - 1], mins[parent[u][i - 1]][i - 1]);
                        maxes[u][i] = Math.max(maxes[u][i - 1], maxes[parent[u][i - 1]][i - 1]);
                        i++;
                    }
                }
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {

            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = xy[0], y = xy[1];

            if (depth[x] > depth[y]) {
                x = xy[1];
                y = xy[0];
            }

            int min = mins[y][0];
            int max = maxes[y][0];

            while (depth[x] < depth[y]) {
                int j = 0;

                while (depth[parent[y][j]] > depth[x]) {
                    j++;
                }

                if (j > 0) j--;

                min = Math.min(min, mins[y][j]);
                max = Math.max(max, maxes[y][j]);
                y = parent[y][j];
            }

            while (x != y) {
                int j = 0;

                while (parent[x][j] != parent[y][j]) {
                    j++;
                }

                if (j > 0) j--;

                min = Math.min(min, mins[x][j]);
                max = Math.max(max, maxes[x][j]);
                x = parent[x][j];

                min = Math.min(min, mins[y][j]);
                max = Math.max(max, maxes[y][j]);
                y = parent[y][j];
            }

            bw.write(min + " " + max + "\n");
        }

        bw.flush();
    }

    static class Edge {
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}