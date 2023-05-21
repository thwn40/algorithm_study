package SeongWoo.week28;

import java.util.List;

public class 양과_늑대 {

    static int result = 0;
    static int[] nodeArr;
    static List<Integer>[] linkedNodeArr;

    private static void dfs(int nodeIndex, int sheep, int wolf, boolean[] checkArr) {
        if (nodeArr[nodeIndex] == 0) {
            sheep++;
        } else {
            wolf++;
        }

    }

    public int solution(int[] info, int[][] edges) {
        nodeArr = info;
        boolean[] checkArr = new boolean[info.length];

        for (int i = 0; i < edges.length; i++) {
            linkedNodeArr[edges[i][0]].add(edges[i][1]);
            linkedNodeArr[edges[i][1]].add(edges[i][0]);

        }

        return 0;
    }
}
