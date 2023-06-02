package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드 {

    private static void initArr(BufferedReader br, int edgeSize, int[][] costArr, int inf) throws IOException {

        StringTokenizer st;

        for (int i = 0; i < costArr.length; i++) {
            for (int j = 0; j < costArr[0].length; j++) {
                if (i == j) {
                    costArr[i][j] = 0;
                } else {
                    costArr[i][j] = inf;
                }
            }
        }

        for (int i = 0; i < edgeSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int fromNode = Integer.parseInt(st.nextToken()) - 1;
            int toNode = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            costArr[fromNode][toNode] = Math.min(costArr[fromNode][toNode], cost);
        }
    }

    private static void floyd(int nodeSize, int[][] costArr) {

        for (int k = 0; k < nodeSize; k++) {
            for (int i = 0; i < costArr.length; i++) {
                for (int j = 0; j < costArr[0].length; j++) {
                    costArr[i][j] = Math.min(costArr[i][j], costArr[i][k] + costArr[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int edgeSize = Integer.parseInt(st.nextToken());
        int[][] costArr = new int[nodeSize][nodeSize];
        int inf = Integer.MAX_VALUE / 2;

        initArr(br, edgeSize, costArr, inf);
        floyd(nodeSize, costArr);

        for (int[] nodeCost : costArr) {
            StringBuilder sb = new StringBuilder();
            for (int cost : nodeCost) {
                if (cost == inf) {
                    sb.append("0 ");
                } else {
                    sb.append(cost).append(" ");
                }
            }
            System.out.println(sb);
        }
    }
}
