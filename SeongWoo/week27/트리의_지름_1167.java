package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의_지름_1167 {

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
        for (Node node : nodeArr) {
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

                nextNode.check = true;
                nextNode.totalCost = currentNode.totalCost + edge.cost;
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

        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 1; i < nodeArr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int nodeNumber = Integer.parseInt(st.nextToken());

            while (true) {
                int linkedNode = Integer.parseInt(st.nextToken());
                if (linkedNode == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());

                nodeArr[nodeNumber].edgeList.add(new Edge(nodeArr[linkedNode], cost));
            }
        }

        Node tempResult = bfs(nodeArr[1]);
        initNodeArr(nodeArr);
        Node maxCostNode = bfs(tempResult);

        System.out.println(maxCostNode.totalCost);
    }
}
