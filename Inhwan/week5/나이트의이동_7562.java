package Inhwan.week5;

import java.io.*;
import java.util.*;

public class 나이트의이동_7562 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int l = Integer.parseInt(br.readLine());
            boolean[][] V = new boolean[l][l];

            Queue<int[]> q1 = new LinkedList<>(), q2 = new LinkedList<>();

            int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] goal = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            q1.add(start);
            V[start[0]][start[1]]=true;

            int[][] D = {{2,1},{1,2},{-2,1},{-1,2},{-2,-1},{-1,-2},{2,-1},{1,-2}};
            int count = 0;

            while (!q1.isEmpty()) {
                int[] p = q1.poll();
                if (p[0]==goal[0] && p[1]==goal[1]) break;

                for (int[] d : D) {
                    int x = p[0]+d[0], y = p[1]+d[1];

                    if (x>=0 && y>=0 && x<l && y<l && !V[x][y]) {
                        q2.add(new int[] {x,y});
                        V[x][y]=true;
                    }
                }

                if (q1.isEmpty()) {
                    q1 = q2;
                    q2 = new LinkedList<>();
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}
