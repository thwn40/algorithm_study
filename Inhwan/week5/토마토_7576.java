package Inhwan.week5;

import java.io.*;
import java.util.*;

public class 토마토_7576 {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] MN = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = MN[0], N = MN[1];

        int[][] Storage = new int[N][];
        for (int i = 0; i < N; i++) Storage[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int blank=0, ripen = 0;

        Queue<int[]> q1= new LinkedList<>(), q2 = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Storage[i][j]==1) {
                    q1.add(new int[] {i,j});
                    ripen++;
                }
                if (Storage[i][j]==-1) blank++;
            }
        }

        int day = 0;
        int[][] D = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};

        while (!q1.isEmpty()) {
            int[] tomato = q1.poll();

            for (int[] d : D) {
                int x = tomato[0]+d[0], y = tomato[1]+d[1];
                if (x>=0 && y>=0 && x<N && y<M && Storage[x][y]==0) {
                    Storage[x][y]=1;
                    ripen++;
                    q2.add(new int[] {x,y});
                }
            }

            if (q1.isEmpty() && !q2.isEmpty()) {
                day++;
                q1=q2;
                q2 = new LinkedList<>();
            }
        }

        if (ripen==N*M-blank) System.out.println(day);
        else System.out.println(-1);
    }
}
