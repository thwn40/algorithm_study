package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의_지름_1967 {

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
        boolean check = false;
        int totalCost = 0;
        List<Edge> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    private static void initNodeArr(Node[] nodeArr) {
        for (int i = 1; i < nodeArr.length; i++) {
            Node node = nodeArr[i];
            node.check = false;
            node.totalCost = 0;
        }
    }

    private static Node bfs(Node firstNode) {
        Node maxCostNode = firstNode;
        Queue<Node> queue = new LinkedList<>();
        firstNode.check = true;
        queue.offer(firstNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (Edge edge : currentNode.edgeList) {
                Node nextNode = edge.to;
                if (nextNode.check) {
                    continue;
                }

                nextNode.totalCost = currentNode.totalCost + edge.cost;
                nextNode.check = true;

                if (nextNode.totalCost > maxCostNode.totalCost) {
                    maxCostNode = nextNode;
                }

                queue.offer(nextNode);
            }
        }
        return maxCostNode;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        Node[] nodeArr = new Node[nodeSize + 1];

        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 1; i < nodeArr.length - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodeArr[from].edgeList.add(new Edge(nodeArr[to], cost));
            nodeArr[to].edgeList.add(new Edge(nodeArr[from], cost));
        }

        Node tempNode = bfs(nodeArr[1]);
        initNodeArr(nodeArr);
        Node maxCostNode = bfs(tempNode);

        System.out.println(maxCostNode.totalCost);
    }
}
