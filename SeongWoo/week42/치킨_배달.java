package SeongWoo.week42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치킨_배달 {

    static int chickenDist = Integer.MAX_VALUE;

    public static class Node {
        int row;
        int col;
        int type;
        boolean check = false;
        boolean isDeleted = false;
        int distant = 0;

        public Node(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }

    public static void initArr(Node[][] nodeArr) {
        Arrays.stream(nodeArr)
                .flatMap(Arrays::stream)
                .forEach(node -> {
                    node.check = false;
                    node.distant = 0;
                });
    }

    public static int getChickenDist(Node[][] nodeArr, List<Node> nodeList) {
        initArr(nodeArr);
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};
        int chickenDist = 0;

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            Node firstNode = nodeList.get(i);
            if (firstNode.isDeleted) {
                continue;
            }
            firstNode.check = true;
            queue.offer(firstNode);
        }

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for (int j = 0; j < 4; j++) {
                int nextRow = currentNode.row + dRow[j];
                int nextCol = currentNode.col + dCol[j];
                if (nextRow < 0 || nextRow >= nodeArr.length || nextCol < 0 || nextCol >= nodeArr[0].length ||
                        nodeArr[nextRow][nextCol].check) {
                    continue;
                }

                Node nextNod = nodeArr[nextRow][nextCol];
                nextNod.distant = currentNode.distant + 1;
                nextNod.check = true;

                if (nextNod.type == 1) {
                    chickenDist += nextNod.distant;
                }
                queue.offer(nextNod);
            }
        }

        return chickenDist;
    }

    public static void combination(Node[][] nodeArr, List<Node> nodeList, int index, int depth, int deleteSize) {
        if (depth == deleteSize) {
            int tempChickenDist = getChickenDist(nodeArr, nodeList);
            if (chickenDist > tempChickenDist) {
                chickenDist = tempChickenDist;
            }
        }

        for (int i = index; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            node.isDeleted = true;
            combination(nodeArr, nodeList, i + 1, depth + 1, deleteSize);
            node.isDeleted = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int size = Integer.parseInt(st.nextToken());
        int liveSize = Integer.parseInt(st.nextToken());

        Node[][] nodeArr = new Node[size][size];
        List<Node> shopList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                Node node = new Node(i, j, Integer.parseInt(st.nextToken()));
                nodeArr[i][j] = node;
                if (node.type == 2) {
                    shopList.add(node);
                }
            }
        }

        int deleteSize = shopList.size() - liveSize;
        combination(nodeArr, shopList, 0, 0, deleteSize);

        System.out.println(chickenDist);
    }
}
