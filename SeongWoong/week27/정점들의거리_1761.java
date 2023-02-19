package SeongWoong.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 정점들의거리_1761 {
    static int N, M, K;
    static int[] level, dist, visit, parent;
    static ArrayList<Edge>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String[] str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0]);
            int to = Integer.parseInt(str[1]);
            int cost = Integer.parseInt(str[2]);
            arr[from].add(new Edge(to, cost));
            arr[to].add(new Edge(from, cost));
        }

        level = new int[N + 1];
        parent = new int[N + 1];
        dist = new int[N + 1];
        visit = new int[N + 1];

        // 바로 상위 부모 찾기
        Queue<Integer> q = new LinkedList<>();

        visit[1] = 1;
        level[1] = 0;
        parent[1] = 0;
        q.add(1);

        while (!q.isEmpty()) {
            int c = q.poll();
            for (Edge e : arr[c]) {
                int n = e.to;
                if (visit[n] == 0) {
                    dist[n] = e.cost;
                    level[n] = level[c] + 1;
                    visit[n] = 1;
                    parent[n] = c;
                    q.add(n);
                }
            }
        }

        // 탐색 시작
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int cost = 0;

            while (level[a] != level[b]) {
                if (level[a] > level[b]) {
                    cost += dist[a];
                    a = parent[a];
                }
                if (level[a] < level[b]) {
                    cost += dist[b];
                    b = parent[b];
                }
            }
            while (a != b) {
                cost += dist[a] + dist[b];
                a = parent[a];
                b = parent[b];
            }
            System.out.println(cost);
        }
    }

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
