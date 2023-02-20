package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리_순회_1991 {

    private static class Node {
        char alp;
        char left;
        char right;

        public Node(char alp, char left, char right) {
            this.alp = alp;
            this.left = left;
            this.right = right;
        }
    }

    private static void freeOrder(Node node, List<Character> orderList, Map<Character, Node> nodeMap) {
        if (node == null) {
            return;
        }

        orderList.add(node.alp);
        freeOrder(nodeMap.get(node.left), orderList, nodeMap);
        freeOrder(nodeMap.get(node.right), orderList, nodeMap);
    }

    private static void inOrder(Node node, List<Character> orderList, Map<Character, Node> nodeMap) {
        if (node == null) {
            return;
        }

        inOrder(nodeMap.get(node.left), orderList, nodeMap);
        orderList.add(node.alp);
        inOrder(nodeMap.get(node.right), orderList, nodeMap);
    }

    private static void postOrder(Node node, List<Character> orderList, Map<Character, Node> nodeMap) {
        if (node == null) {
            return;
        }

        postOrder(nodeMap.get(node.left), orderList, nodeMap);
        postOrder(nodeMap.get(node.right), orderList, nodeMap);
        orderList.add(node.alp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        Map<Character, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < nodeSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char alp = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            Node node = new Node(alp, left, right);
            nodeMap.put(alp, node);
        }

        List<Character> orderList = new ArrayList<>();
        freeOrder(nodeMap.get('A'), orderList, nodeMap);
        orderList.forEach(System.out::print);
        System.out.println();

        orderList = new ArrayList<>();
        inOrder(nodeMap.get('A'), orderList, nodeMap);
        orderList.forEach(System.out::print);
        System.out.println();

        orderList = new ArrayList<>();
        postOrder(nodeMap.get('A'), orderList, nodeMap);
        orderList.forEach(System.out::print);
        System.out.println();
    }
}
