package SeongWoo.week33;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 전력망을_돌로_나누기 {

    static int minDiff = Integer.MAX_VALUE;

    public static class Node {
        int number;
        boolean check = false;
        List<Node> linkedNodeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    public static int getDiff(Node[] nodeArr) {

        int count = 0;
        Queue<Node> queue = new LinkedList<>();
        Node firstNode = nodeArr[1];

        firstNode.check = true;
        count++;
        queue.offer(firstNode);

        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();

            for (Node nextNode : currentNode.linkedNodeList) {
                if (nextNode.check) {
                    continue;
                }
                nextNode.check = true;
                count++;
                queue.offer(nextNode);
            }
        }

        return Math.abs(nodeArr.length - 1 - 2 * count);
    }

    private static Node[] initNodeArr(int n, int[][] wires, int index) {

        Node[] nodeArr = new Node[n + 1];

        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 0; i < wires.length; i++) {
            if (i == index) {
                continue;
            }
            int from = wires[i][0];
            int to = wires[i][1];

            nodeArr[from].linkedNodeList.add(nodeArr[to]);
            nodeArr[to].linkedNodeList.add(nodeArr[from]);
        }

        return nodeArr;
    }

    public int solution(int n, int[][] wires) {

        for (int i = 0; i < wires.length; i++) {
            Node[] nodeArr = initNodeArr(n, wires, i);

            int tempDiff = getDiff(nodeArr);
            if (tempDiff < minDiff) {
                minDiff = tempDiff;
            }
        }

        return minDiff;
    }
}
