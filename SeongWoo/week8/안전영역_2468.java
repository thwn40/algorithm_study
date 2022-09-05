package SeongWoo.week8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 안전영역_2468 {

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    //노드(지역)을 나타내는 클래스
    private static class Node {
        int row;
        int col;
        int height;
        boolean check = false;
        //침수 여부를 나타내는 flag
        boolean flooding = false;

        public Node(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    //주어진 위치로부터 연결된 영역을 탐색
    private static void bfs(Node[][] nodeArr, int row, int col) {
        Queue<Node> queue = new LinkedList<>();
        Node startNode = nodeArr[row][col];
        startNode.check = true;
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = node.row + dRow[i];
                int nextCol = node.col + dCol[i];
                if (nextRow < 0 || nextRow >= nodeArr.length || nextCol < 0 || nextCol >= nodeArr.length
                        || nodeArr[nextRow][nextCol].check || nodeArr[nextRow][nextCol].flooding) {
                    continue;
                }
                Node nextNode = nodeArr[nextRow][nextCol];
                nextNode.check = true;
                queue.offer(nextNode);
            }
        }
    }

    //주어진 nodeArr에서 안전한 영역의 개수를 찾는 메서드
    private static int getCount(Node[][] nodeArr) {
        int count = 0;
        for (int i = 0; i < nodeArr.length; i++) {
            for (int j = 0; j < nodeArr.length; j++) {
                if (nodeArr[i][j].check || nodeArr[i][j].flooding) {
                    continue;
                }
                bfs(nodeArr, i, j);
                count++;
            }
        }

        return count;
    }

    //강수량을 0부터 100까지 높히면서 각 경우에 안전한 영역의 개수를 탐색하고 그 최대값을 구하는 메서드
    private static int getMaxCount(Node[][] nodeArr) {

        int maxCount = 0;

        for (int rainHeight = 0; rainHeight <= 100; rainHeight++) {
            //nodeArr의 모든 check를 false로 초기화한다.
            initNode(nodeArr);
            //주어진 강수량 만큼 nodeArr의 node를 침수시킨다.
            flood(nodeArr, rainHeight);
            int count = getCount(nodeArr);
            //전 지역이 침수되었다면 반복문을 미리 종료한다.
            if (count == 0) {
                break;
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    //주어진 nodeArr에서 node의 check를 전부 false로 초기화하는 메서드
    private static void initNode(Node[][] nodeArr) {
        for (int i = 0; i < nodeArr.length; i++) {
            for (int j = 0; j < nodeArr.length; j++) {
                if (nodeArr[i][j].flooding) {
                    continue;
                }
                nodeArr[i][j].check = false;
            }
        }
    }

    //주어진 강수량에 따라 nodeArr의 node를 침수시키는 메서드
    private static void flood(Node[][] nodeArr, int rainHeight) {
        for (int i = 0; i < nodeArr.length; i++) {
            for (int j = 0; j < nodeArr.length; j++) {
                //강수량은 증가하도록 주어지기 때문에 이미 침수된 지역은 넘어간다.
                if (nodeArr[i][j].flooding) {
                    continue;
                }
                if (nodeArr[i][j].height <= rainHeight) {
                    nodeArr[i][j].flooding = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Node[][] nodeArr = new Node[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int height = scanner.nextInt();
                nodeArr[i][j] = new Node(i, j, height);
            }
        }

        int count = getMaxCount(nodeArr);
        System.out.println(count);
    }
}
