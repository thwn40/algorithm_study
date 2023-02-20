package Inhwan.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 배열에서이동_1981 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] table = new int[n][];
        for (int i = 0; i < n; i++) {
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        if (test(table, 0)) {
            System.out.println(0);
            return;
        }

        int s = 0;
        int e = 200;

        while (e - s > 1) {
            int m = (e + s) / 2;

            if (test(table, m)) e = m;
            else s = m;
        }

        System.out.println(e);
    }

    static boolean test(int[][] table, int t) {

        int n = table.length;
        int[][] D = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        for (int i = 0; i + t <= 200; i++) {
            int j = i + t;

            Queue<int[]> q = new LinkedList<>();

            if (table[0][0] >= i && table[0][0] <= j) q.add(new int[]{0, 0});

            boolean[][] visited = new boolean[n][];
            for (int k = 0; k < n; k++) {
                visited[k] = new boolean[n];
            }
            visited[0][0] = true;

            while (!q.isEmpty()) {
                int[] now = q.poll();

                if (now[0] == n - 1 && now[1] == n - 1) return true;

                for (int[] d : D) {
                    int x = now[0] + d[0];
                    int y = now[1] + d[1];

                    if (x >= 0 && y >= 0 && x < n && y < n && !visited[x][y] && table[x][y] >= i && table[x][y] <= j) {
                        q.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
        }

        return false;
    }
}