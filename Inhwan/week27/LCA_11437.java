package Inhwan.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LCA_11437 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] parents = new int[n + 1];
        int[] depth = new int[n + 1];

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n - 1; i++) {
            q.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int n1 = node[0], n2 = node[1];

            if (n1 == 1 || parents[n1] > 0) {
                parents[n2] = n1;
                depth[n2] = depth[n1] + 1;
            } else if (n2 == 1 || parents[n2] > 0) {
                parents[n1] = n2;
                depth[n1] = depth[n2] + 1;
            } else {
                q.add(node);
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = xy[0], y = xy[1];

            while (x != y) {
                if (depth[x] > depth[y]) x = parents[x];
                else if (depth[y] > depth[x]) y = parents[y];
                else {
                    x = parents[x];
                    y = parents[y];
                }
            }

            System.out.println(x);
        }
    }
}