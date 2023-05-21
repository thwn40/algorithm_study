package SeongWoo.week37;

import java.util.HashMap;
import java.util.Map;

public class 방문_길이 {

    public static class Node {
        int row;
        int col;
        boolean[] checkArr = new boolean[4];

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class MoveInfo {
        int dRow;
        int dCol;
        int from;
        int to;

        public MoveInfo(int dRow, int dCol, int from, int to) {
            this.dRow = dRow;
            this.dCol = dCol;
            this.from = from;
            this.to = to;
        }
    }


    public int solution(String dirs) {
        int answer = 0;
        Node[][] nodeArr = new Node[11][11];
        Map<Character, MoveInfo> moveMap = new HashMap<>();

        for (int i = 0; i < nodeArr.length; i++) {
            for (int j = 0; j < nodeArr[0].length; j++) {
                nodeArr[i][j] = new Node(i, j);
            }
        }

        moveMap.put('U', new MoveInfo(-1, 0, 0, 2));
        moveMap.put('R', new MoveInfo(0, 1, 1, 3));
        moveMap.put('D', new MoveInfo(1, 0, 2, 0));
        moveMap.put('L', new MoveInfo(0, -1, 3, 1));

        Node currentNode = nodeArr[5][5];

        for (int i = 0; i < dirs.length(); i++) {
            char move = dirs.charAt(i);
            MoveInfo moveInfo = moveMap.get(move);

            int nextRow = currentNode.row + moveInfo.dRow;
            int nextCol = currentNode.col + moveInfo.dCol;

            if (nextRow < 0 || nextRow >= nodeArr.length || nextCol < 0 || nextCol >= nodeArr[0].length) {
                continue;
            }

            Node nextNode = nodeArr[nextRow][nextCol];

            if (!currentNode.checkArr[moveInfo.from]) {
                currentNode.checkArr[moveInfo.from] = true;
                nextNode.checkArr[moveInfo.to] = true;
                answer++;
            }

            currentNode = nextNode;
        }

        return answer;
    }
}
