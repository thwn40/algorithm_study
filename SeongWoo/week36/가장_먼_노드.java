package SeongWoo.week36;

import java.util.*;
import java.util.stream.Collectors;

public class 가장_먼_노드 {
    static ArrayList<Integer>[] edgeList;
    static boolean[] check;
    static int[] dist;

    private void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        check[start] = true;
        dist[start] = 1;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nextNode : edgeList[node]) {
                if (!check[nextNode]) {
                    check[nextNode] = true;
                    dist[nextNode] = dist[node] + 1;
                    queue.offer(nextNode);
                }
            }
        }
    }

    public int solution(int n, int[][]edge) {

        check = new boolean[n + 1];
        dist = new int[n + 1];
        edgeList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int[] ints : edge) {
            int node = ints[0];
            edgeList[node].add(ints[1]);
            edgeList[ints[1]].add(node);
        }

        bfs(1);
        List<Integer> collect = Arrays.stream(dist)
                .boxed()
                .collect(Collectors.toList());
        Integer max = Collections.max(collect);
        int frequency = Collections.frequency(collect, max);

        return frequency;
    }

}
