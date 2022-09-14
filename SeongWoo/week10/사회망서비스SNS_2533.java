package SeongWoo.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 사회망서비스SNS_2533 {

    private static class Node {
        public int number;
        public boolean adapter = false;
        public List<Node> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    private static boolean dp(Node node, Node parentNode) {
        List<Node> edgeList = node.edgeList;

        for (int i = 0; i < edgeList.size(); i++) {
            Node nextNode = edgeList.get(i);
            if (nextNode.equals(parentNode)) {
                continue;
            }
            if (!dp(nextNode, node)) {
                node.adapter = true;
            }
        }
        return node.adapter;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeSize = Integer.parseInt(br.readLine());
        Node[] nodeArr = new Node[nodeSize + 1];

        for (int i = 0; i <= nodeSize; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 0; i < nodeSize - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodeArr[from].edgeList.add(nodeArr[to]);
            nodeArr[to].edgeList.add(nodeArr[from]);
        }

        dp(nodeArr[1], nodeArr[1]);

        long count = Arrays.stream(nodeArr)
                .filter(node -> node.adapter)
                .count();
        System.out.println(count);
    }
}
