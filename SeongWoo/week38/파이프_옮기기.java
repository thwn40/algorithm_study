package SeongWoo.week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 파이프_옮기기 {

    public static class Node {
        int row;
        int col;
        int block;
        boolean check = false;
        int[] numberCase = {0, 0, 0};

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void calculateCase(Node[][] nodeArr) {

            Node leftNode = nodeArr[row][col - 1];
            Node upNode = nodeArr[row - 1][col];
            Node diagonalNode = nodeArr[row - 1][col - 1];

            numberCase[0] = leftNode.numberCase[0] + leftNode.numberCase[2];
            numberCase[1] = upNode.numberCase[1] + upNode.numberCase[2];
            if (upNode.block == 0 && leftNode.block == 0) {
                numberCase[2] = diagonalNode.numberCase[0] + diagonalNode.numberCase[1] + diagonalNode.numberCase[2];
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        Node[][] nodeArr = new Node[size + 1][size + 1];

        for (int i = 0; i < nodeArr.length; i++) {
            for (int j = 0; j < nodeArr[0].length; j++) {
                nodeArr[i][j] = new Node(i, j);
            }
        }

        for (int i = 1; i < nodeArr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < nodeArr[0].length; j++) {
                nodeArr[i][j].block = Integer.parseInt(st.nextToken());
            }
        }

        nodeArr[1][2].numberCase[0] = 1;
        nodeArr[1][2].check = true;

        for (int i = 1; i < nodeArr.length; i++) {
            for (int j = 2; j < nodeArr[0].length; j++) {
                Node node = nodeArr[i][j];
                if (node.check || node.block == 1) {
                    continue;
                }
                node.calculateCase(nodeArr);
                node.check = true;
            }
        }

        int result = Arrays.stream(nodeArr[size][size].numberCase)
                .sum();

        System.out.println(result);
    }
}
