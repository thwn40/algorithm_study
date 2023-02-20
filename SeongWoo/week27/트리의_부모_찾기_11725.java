package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의_부모_찾기_11725 {

    private static class Node {
        int number;
        boolean check = false;
        Node parent;
        List<Node> nodeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    private static void bfs(Node firstNode) {
        Queue<Node> queue = new LinkedList<>();
        firstNode.check = true;
        queue.offer(firstNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (Node nextNode : currentNode.nodeList) {
                if (nextNode.check) {
                    continue;
                }
                nextNode.check = true;
                nextNode.parent = currentNode;
                queue.offer(nextNode);
            }
        }
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

            nodeArr[from].nodeList.add(nodeArr[to]);
            nodeArr[to].nodeList.add(nodeArr[from]);
        }

        bfs(nodeArr[1]);

        for (int i = 2; i < nodeArr.length; i++) {
            Node node = nodeArr[i];
            System.out.println(node.parent.number);
        }
    }
}
