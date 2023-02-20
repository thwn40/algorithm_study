package Inhwan.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정점들의거리_1761 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] parents = new int[n + 1];
        int[] depth = new int[n + 1];
        int[] distanceFromRoot = new int[n + 1];

        List<Edge>[] adj = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n1 = edge[0], n2 = edge[1], distance = edge[2];

            adj[n1].add(new Edge(n2, distance));
            adj[n2].add(new Edge(n1, distance));
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int p = q.poll();

            for (Edge edge : adj[p]) {
                if (edge.to > 1 && depth[edge.to] == 0) {
                    parents[edge.to] = p;
                    depth[edge.to] = depth[p] + 1;
                    distanceFromRoot[edge.to] = distanceFromRoot[p] + edge.distance;
                    q.add(edge.to);
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = xy[0], y = xy[1];
            int X = x, Y = y;

            while (X != Y) {
                if (depth[X] > depth[Y]) X = parents[X];
                else if (depth[Y] > depth[X]) Y = parents[Y];
                else {
                    X = parents[X];
                    Y = parents[Y];
                }
            }

            System.out.println(distanceFromRoot[x] + distanceFromRoot[y] - 2 * distanceFromRoot[X]);
        }
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