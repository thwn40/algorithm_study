package Inhwan.week27;

import java.io.*;
import java.util.*;

public class LCA2_11438 {
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

        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int v1 = edge[0], v2 = edge[1];

            adj[v1].add(v2);
            adj[v2].add(v1);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (Integer u : adj[v]) {
                if (u > 1 && depth[u] == 0) {
                    q.add(u);

                    depth[u] = depth[v] + 1;
                    parent[u][0] = v;
                    int i = 1;

                    while (true) {
                        parent[u][i] = parent[parent[u][i - 1]][i - 1];
                        if (parent[u][i] == 0) break;
                        i++;
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {

            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = xy[0], y = xy[1];

            if (depth[x] > depth[y]) {
                x = xy[1];
                y = xy[0];
            }

            while (depth[x] < depth[y]) {
                int j = 0;

                while (depth[parent[y][j]] > depth[x]) {
                    j++;
                }

                if (j > 0) j--;

                y = parent[y][j];
            }

            while (x != y) {
                int j = 0;

                while (parent[x][j] != parent[y][j]) {
                    j++;
                }

                if (j > 0) j--;

                x = parent[x][j];
                y = parent[y][j];
            }

            bw.write(x + "\n");
        }

        bw.flush();
    }
}