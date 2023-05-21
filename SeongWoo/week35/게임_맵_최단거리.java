package SeongWoo.week35;

import java.util.LinkedList;
import java.util.Queue;

public class 게임_맵_최단거리 {

    public static class Node {
        int row;
        int col;
        boolean check = false;
        int road;
        int dist = 0;

        public Node(int row, int col, int road) {
            this.row = row;
            this.col = col;
            this.road = road;
        }
    }

    public int bfs(Node[][] nodeArr) {

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};
        Queue<Node> queue = new LinkedList<>();
        Node firstNode = nodeArr[0][0];

        firstNode.check = true;
        firstNode.dist = 1;
        queue.offer(firstNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.equals(nodeArr[nodeArr.length - 1][nodeArr[0].length - 1])) {
                return currentNode.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = currentNode.row + dRow[i];
                int nextCol = currentNode.col + dCol[i];

                if (nextRow < 0 || nextRow >= nodeArr.length || nextCol < 0 || nextCol >= nodeArr[0].length
                        || nodeArr[nextRow][nextCol].road == 0 || nodeArr[nextRow][nextCol].check) {
                    continue;
                }

                Node nextNode = nodeArr[nextRow][nextCol];
                nextNode.check = true;
                nextNode.dist = currentNode.dist + 1;
                queue.offer(nextNode);
            }
        }

        return  -1;
    }

    public int solution(int[][] maps) {

        Node[][] nodeArr = new Node[maps.length][maps[0].length];

        for (int i = 0; i < nodeArr.length; i++) {
            for (int j = 0; j < nodeArr[0].length; j++) {
                nodeArr[i][j] = new Node(i, j, maps[i][j]);
            }
        }

        return bfs(nodeArr);
    }
}
