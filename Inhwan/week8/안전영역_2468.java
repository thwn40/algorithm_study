package Inhwan.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 안전영역_2468 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] Region = new int[N][N];
        for (int i = 0; i < N; i++) Region[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int safe = 1, max = 1;
        int h = 1;

        while (safe > 0) {
            safe = countSafeZone(h++, Region);
            max = Math.max(max, safe);
        }

        System.out.println(max);
    }

    static int countSafeZone(int h, int[][] Region) {
        int count = 0;
        int N = Region.length;
        boolean[][] Checked = new boolean[N][N];

        Queue<int[]> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        int[][] move = {{0,1},{1,0},{0,-1},{-1,0}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Region[i][j] <= h) Checked[i][j] = true;
                else if (!Checked[i][j]) {
                    count++;
                    q1.add(new int[] {i,j});
                    Checked[i][j] = true;

                    while (!q1.isEmpty()) {
                        int[] now = q1.poll();

                        for (int[] m : move) {
                            int i_ = now[0] + m[0], j_ = now[1] + m[1];
                            if (0 <= i_ && 0 <= j_ && i_ < N && j_ < N && !Checked[i_][j_] && Region[i_][j_] > h) {
                                q2.add(new int[] {i_,j_});
                                Checked[i_][j_] = true;
                            }
                        }

                        if (q1.isEmpty()) {
                            q1 = q2;
                            q2 = new LinkedList<>();
                        }
                    }
                }
            }
        }

        return count;
    }
}