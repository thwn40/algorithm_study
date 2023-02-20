package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LCA_11437 {

    private static class Node {
        int number;
        boolean check = false;
        int depth = 0;
        Node parents;
        List<Node> linkedNodeList = new ArrayList<>();

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

            for (Node nextNode : currentNode.linkedNodeList) {
                if (nextNode.check) {
                    continue;
                }

                nextNode.depth = currentNode.depth + 1;
                nextNode.parents = currentNode;
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

        while (deepNode.depth != shallowNode.depth) {
            deepNode = deepNode.parents;
        }

        while (deepNode.number != shallowNode.number) {
            deepNode = deepNode.parents;
            shallowNode = shallowNode.parents;
        }

        return deepNode.number;
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

            nodeArr[from].linkedNodeList.add(nodeArr[to]);
            nodeArr[to].linkedNodeList.add(nodeArr[from]);
        }

        bfs(nodeArr[1]);

        st = new StringTokenizer(br.readLine());
        int caseNumber = Integer.parseInt(st.nextToken());

        while (caseNumber-- > 0) {
            st = new StringTokenizer(br.readLine());
            int deep = Integer.parseInt(st.nextToken());
            int shallow = Integer.parseInt(st.nextToken());

            int lca = lca(nodeArr[deep], nodeArr[shallow]);
            System.out.println(lca);
        }
    }
}
