package SeongWoo.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크_연결_1922 {

    private static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static int find(int[] union, int number) {
        if (union[number] == number) {
            return number;
        }
        return find(union, union[number]);
    }

    private static void union(int[] union, int fromParent, int toParent) {
        int fromRoot = find(union, fromParent);
        int toRoot = find(union, toParent);

        union[fromRoot] = toRoot;
    }

    private static int kruskal(PriorityQueue<Edge> queue, int[] union) {
        int result = 0;
        int count = 0;
        int size = union.length - 1;

        while (count < size - 1) {
            Edge currentEdge = queue.poll();

            int fromParent = find(union, currentEdge.from);
            int toParent = find(union, currentEdge.to);
            if (fromParent != toParent) {
                result += currentEdge.cost;
                count++;
                union(union, fromParent, toParent);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int edgeSize = Integer.parseInt(st.nextToken());

        int[] union = new int[nodeSize + 1];
        for (int i = 1; i < union.length; i++) {
            union[i] = i;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        for (int i = 0; i < edgeSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            queue.offer(new Edge(from, to, cost));
        }

        int result = kruskal(queue, union);

        System.out.println(result);
    }
}
