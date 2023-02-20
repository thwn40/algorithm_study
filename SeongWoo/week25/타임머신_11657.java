package SeongWoo.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 타임머신_11657 {

    static final Integer INF = Integer.MAX_VALUE;

    private static class Edge {
        Node to;
        int cost;

        public Edge(Node to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class Node {
        int number;
        long totalPayOff = INF;
        List<Edge> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    private static boolean bellmanFord(Node[] nodeArr) {
        for (int i = 0; i < nodeArr.length - 1; i++) {
            for (int j = 1; j < nodeArr.length; j++) {
                Node from = nodeArr[j];
                if (from.totalPayOff == INF) {
                    continue;
                }
                for (Edge edge : from.edgeList) {
                    Node to = edge.to;
                    long totalPayOff = from.totalPayOff + edge.cost;
                    if (totalPayOff >= to.totalPayOff) {
                        continue;
                    }
                    if (i == nodeArr.length - 2) {
                        return false;
                    }
                    to.totalPayOff = totalPayOff;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int nodeSize = Integer.parseInt(st.nextToken());
        int edgeSize = Integer.parseInt(st.nextToken());
        Node[] nodeArr = new Node[nodeSize + 1];

        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 0; i < edgeSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodeArr[from].edgeList.add(new Edge(nodeArr[to], cost));
        }

        nodeArr[1].totalPayOff = 0L;
        if (bellmanFord(nodeArr)) {
            for (int i = 2; i < nodeArr.length; i++) {
                Node node = nodeArr[i];
                if (node.totalPayOff == INF) {
                    System.out.println(-1);
                } else {
                    System.out.println(node.totalPayOff);
                }
            }
        } else {
            System.out.println(-1);
        }
    }
}
