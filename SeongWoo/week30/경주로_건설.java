package SeongWoo.week30;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class 경주로_건설 {

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    public static class Node {
        int block;
        int row;
        int col;
        ArrayList<Node> beforeNodeList = new ArrayList<>();
        boolean check = false;
        int totalPayOff = Integer.MAX_VALUE;
//        int[] totalPaOffArr = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

        public Node(int block, int row, int col) {
            this.block = block;
            this.row = row;
            this.col = col;
        }

        public int getPayOff(Node nextNode) {
            if (beforeNodeList.isEmpty()) {
                return 100;
            }
            for (Node beforeNode : beforeNodeList) {
                if (beforeNode.row == row && row == nextNode.row ||
                        beforeNode.col == col && col == nextNode.col) {
                    return 100;
                }
            }
            return 600;
        }
    }

    public static void dijkstra(Node firstNode, Node[][] nodeArr) {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.totalPayOff - n2.totalPayOff);
        firstNode.totalPayOff = 0;
        queue.offer(firstNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            currentNode.check = true;

            for (int i = 0; i < 4; i++) {
                int nextRow = currentNode.row + dRow[i];
                int nextCol = currentNode.col + dCol[i];

                if (nextRow >= nodeArr.length || nextRow < 0 || nextCol >= nodeArr[0].length || nextCol < 0 ||
                        nodeArr[nextRow][nextCol].block == 1) {
                    continue;
                }

                Node nextNode = nodeArr[nextRow][nextCol];
                int payOff = currentNode.getPayOff(nextNode);
                int totalPayOff = currentNode.totalPayOff + payOff;
                if (nextNode.totalPayOff < totalPayOff) {
                    continue;
                }

                nextNode.totalPayOff = totalPayOff;
                nextNode.beforeNodeList.add(currentNode);
                queue.offer(nextNode);
            }
        }
    }


    public int solution(int[][] board) {
        Node[][] nodeArr = new Node[board.length][board[0].length];

        for (int i = 0; i < nodeArr.length; i++) {
            for (int j = 0; j < nodeArr[0].length; j++) {
                nodeArr[i][j] = new Node(board[i][j], i, j);
            }
        }

        dijkstra(nodeArr[0][0], nodeArr);

        return nodeArr[nodeArr.length - 1][nodeArr[0].length - 1].totalPayOff;
    }
}
