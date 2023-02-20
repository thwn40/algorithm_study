package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정점들의_거리_1761 {
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
        int depth = 0;
        Node parent;
        int toParentCost = 0;
        List<Edge> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    private static void bfs(Node fisrtNode) {
        Queue<Node> queue = new LinkedList<>();
        fisrtNode.check = true;
        queue.offer(fisrtNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (Edge edge : currentNode.edgeList) {
                Node nextNode = edge.to;
                if (nextNode.check) {
                    continue;
                }

                nextNode.depth = currentNode.depth + 1;
                nextNode.parent = currentNode;
                nextNode.toParentCost = edge.cost;
                nextNode.check = true;
                queue.offer(nextNode);
            }
        }
    }

    private static int lca(Node deepNode, Node shallowNode) {
        if (deepNode.depth < shallowNode.depth) {
            Node temp = deepNode;
            deepNode = shallowNode;
            shallowNode = temp;
        }

        int deepNodeCost = 0;
        int shallowNodeCost = 0;

        while (deepNode.depth != shallowNode.depth) {
            deepNodeCost += deepNode.toParentCost;
            deepNode = deepNode.parent;

        }

        while (deepNode.number != shallowNode.number) {
            deepNodeCost += deepNode.toParentCost;
            shallowNodeCost += shallowNode.toParentCost;
            deepNode = deepNode.parent;
            shallowNode = shallowNode.parent;
        }

        return deepNodeCost + shallowNodeCost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        Node[] nodeArr = new Node[nodeSize + 1];

        for (int i = 0; i < nodeArr.length; i++) {
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

        bfs(nodeArr[1]);

        st = new StringTokenizer(br.readLine());
        int caseSize = Integer.parseInt(st.nextToken());

        while (caseSize-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int dist = lca(nodeArr[from], nodeArr[to]);
            System.out.println(dist);
        }
    }
}
