package SeongWoo.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길_1520 {

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    private static class Node {
        private int row;
        private int col;
        private int height;
        //시작점에서 해당 노드로 갈 수 있는 경우의 수
        private int caseNumber = 0;
        private boolean check = false;

        public Node(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    //점화식 : d[i][j]를 시작점에서 해당 위치로 갈 수 있는 경우의 수라고 한다면
    //d[i][j] = sum(d[i - 1][j], d[i][j + 1], d[i - 1][j], d[i][j - 1])
    private static Node dp(Node node, Node[][] nodeArr, Node startNode) {
        //해당 노드의 caseNumber가 계산됬다면 점화식을 돌리지 않고 바로 그 노드를 반환
        if (node.check) {
            return node;
        }
        if (node == startNode) {
            return startNode;
        }

        //d[i][j] = sum(d[i - 1][j], d[i][j + 1], d[i - 1][j], d[i][j - 1])
        //d[i][j] = nodeArr[i][j].caseNumber
        //nodeArr[i - 1][j], nodeArr[i][j + 1], nodeArr[i - 1][j], nodeArr[i][j - 1] 에서
        //nodeArr[i][j]보다 높이가 낮은 node는 제외
        for (int i = 0; i < 4; i++) {
            int beforeRow = node.row + dRow[i];
            int beforeCol = node.col + dCol[i];
            if (beforeRow < 0 || beforeRow >= nodeArr.length || beforeCol < 0 || beforeCol >= nodeArr[0].length
                    || nodeArr[beforeRow][beforeCol].height <= node.height) {
                continue;
            }
            Node beforeNode = dp(nodeArr[beforeRow][beforeCol], nodeArr, startNode);
            node.caseNumber += beforeNode.caseNumber;
        }
        node.check = true;

        return node;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int rowSize = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());
        Node[][] nodeArr = new Node[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < colSize; j++) {
                nodeArr[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        nodeArr[0][0].caseNumber = 1;
        dp(nodeArr[rowSize - 1][colSize - 1], nodeArr, nodeArr[0][0]);

        System.out.println(nodeArr[rowSize - 1][colSize - 1].caseNumber);
    }
}
