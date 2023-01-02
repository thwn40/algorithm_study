package SeongWoo.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 알고스팟_1261 {

    static int[] rowSet = {-1, 0, 1, 0};
    static int[] colSet = {0, 1, 0, -1};

    private static class Node {

        int row;
        int col;
        int count;
        int wall;
        boolean check;

        public Node(int row, int col, int count, int wall, boolean check) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.wall = wall;
            this.check = check;
        }
    }

    private static Node bfs(Node firstNode, int distRow, int distCol, Node[][] nodeArr) {

        Deque<Node> deque = new LinkedList<>();
        deque.offer(firstNode);
        firstNode.check = true;

        while (!deque.isEmpty()) {

            Node currentNode = deque.poll();

            if (currentNode.row == distRow & currentNode.col == distCol) {
                return currentNode;
            }

            for (int i = 0; i < 4; i++) {

                int nextRow = currentNode.row + rowSet[i];
                int nextCol = currentNode.col + colSet[i];

                if (nextRow < 0 || nextCol < 0 || nextRow > distRow || nextCol > distCol
                        || nodeArr[nextRow][nextCol].check) {
                    continue;
                }

                Node nextNode = nodeArr[nextRow][nextCol];
                if (nextNode.wall == 1) {
                    nextNode.count = currentNode.count + 1;
                    deque.offer(nextNode);
                } else {
                    nextNode.count = currentNode.count;
                    deque.push(nextNode);
                }
                nextNode.check = true;
            }
        }

        return null;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        Node[][] nodeArr = new Node[row][col];

        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < col; j++) {
                int wall = Integer.parseInt(String.valueOf(line.charAt(j)));
                nodeArr[i][j] = new Node(i, j, 0, wall, false);
            }
        }

        Node result = bfs(nodeArr[0][0], row - 1, col - 1, nodeArr);

        System.out.println(result.count);
    }

}
