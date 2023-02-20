import java.util.*;

public class 최소스패닝트리_1197_크루스칼 {
    static int[] root;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<Edge> list = new ArrayList<>();
        int answer = 0;

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            long cost = sc.nextInt();
            list.add(new Edge(start,end,cost));
        }

        Collections.sort(list);

        root = new int[N + 1];

        for (int i = 0; i < N+1; i++) {
            root[i] = i;
        }
        for (Edge edge : list) {
            int rootX = find(edge.start);
            int rootY = find(edge.end);
            if (rootX != rootY) {
                union(rootX, rootY);
                answer += edge.cost;
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

    // 간선에서 시작점을 찾기
    public static int find(int x) {
        if (root[x] == x) {
            return x;
        } else {
            return root[x] = find(root[x]);
        }
    }

    // 두 간선을 연결 하여 뿌리를 같게 만든다.
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = x;
        }
    }
}
