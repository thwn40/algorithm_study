import java.util.*;

public class 최소스패닝트리_1197 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<Edge>[] list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            long cost = sc.nextInt();
            list[start].add(new Edge(start, end, cost));
            list[end].add(new Edge(end, start, cost));
        }
        Queue<Edge> q = new PriorityQueue<>();

        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        for (Edge edge : list[1]){
            q.add(edge);
        }
        int answer = 0;
        while (!q.isEmpty()) {
            Edge cur = q.poll();
            if (visited[cur.end] == true) {
                continue;
            }
            visited[cur.end] = true;
            answer += cur.cost;

            for (Edge next : list[cur.end]) {
                q.add(next);
            }
        }
        System.out.println(answer);
    }
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        long cost;
        public Edge(int start, int end, long cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return (int)(cost - o.cost);
        }
    }
}
