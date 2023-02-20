package SeongWoo.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 최소_스패닝_트리_1197 {

    private static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static int find(int[] parentArr, int number) {
        if (parentArr[number] == number) {
            return number;
        }
        return (parentArr[number] = find(parentArr, parentArr[number]));
    }

    private static long kruskal(List<Edge> edgeList, int[] parentArr) {
        int result = 0;
        int count = 0;
        int size = parentArr.length - 1;

        for (Edge currentEdge : edgeList) {
            int fromParent = find(parentArr, currentEdge.from);
            int toParent = find(parentArr, currentEdge.to);
            if (fromParent != toParent) {
                result += currentEdge.cost;
                count++;
                parentArr[fromParent] = parentArr[toParent];
            }
            if (count == size - 1) {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int nodeSize = Integer.parseInt(st.nextToken());
        int edgeSize = Integer.parseInt(st.nextToken());
        int[] parentArr = new int[nodeSize + 1];
        List<Edge> edgeList = new ArrayList<>();

        for (int i = 1; i < parentArr.length; i++) {
            parentArr[i] = i;
        }

        for (int i = 0; i < edgeSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(from, to, cost));
        }

        edgeList.sort((e1, e2) -> e1.cost - e2.cost);

        long result = kruskal(edgeList, parentArr);

        System.out.println(result);
    }
}
