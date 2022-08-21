package Inhwan.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 정육면체전개도_1917 {
    static int[][] Planar;
    static boolean[][] check = new boolean[6][];
    static boolean temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 3; t++) {
            boolean isCube = true;

            Planar = new int[6][];
            for (int i = 0; i < 6; i++) {
                Planar[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {

                    if (Planar[i][j]==1) {
                        temp = false;

                        for (int k = 0; k < 6; k++) {
                            check[k] = new boolean[6];
                        }

                        Planar[i][j] = 2;
                        check[i][j] = true;

                        List<Integer> X = new ArrayList<>(), Y=new ArrayList<>();
                        X.add(0);
                        Y.add(0);
                        dfs(i,j,X ,Y,0);

                        isCube = isCube && temp;
                    }
                }
            }

            if (isCube) System.out.println("yes");
            else System.out.println("no");
        }
    }

    static void dfs(int i, int j, List<Integer> X, List<Integer> Y, int d) {
        int x = X.stream().mapToInt(Integer::intValue).sum();
        int y = Y.stream().mapToInt(Integer::intValue).sum();

        if (Planar[i+x][j+y]==1 && test(X,Y,d)) {
            Planar[i+x][j+y]=2;
            temp=true;
            return;
        }

        int[][] D = {{1,0},{0,1},{-1,0},{0,-1}};

        for (int[] dir : D) {
            if (temp) return;

            x+=dir[0];
            y+=dir[1];

            if (i+x>=0 && j+y>=0 && i+x<6 && j+y<6 && !check[i+x][j+y] && Planar[i+x][j+y]>0) {
                check[i+x][j+y]=true;
                X.add(dir[0]);
                Y.add(dir[1]);
                dfs(i,j,X,Y,d+1);
                check[i+x][j+y]=false;
                X.remove(d+1);
                Y.remove(d+1);
            }
            x-=dir[0];
            y-=dir[1];
        }
    }

    static boolean test(List<Integer> X, List<Integer> Y, int d) {
        int x = X.stream().mapToInt(Integer::intValue).sum();
        int y = Y.stream().mapToInt(Integer::intValue).sum();

        if (d==2 && x*y==0) return true;
        if (d==3 && x*x*y*y==4 && X.get(1)==X.get(3) && Y.get(1)==Y.get(3)) return true;
        if (d==4 && x*x*y*y==16 && X.get(1)==X.get(4) && Y.get(1)==Y.get(4)) return true;
        if (d==5 && x*x*y*y==36 && (X.get(1)+X.get(5))*(X.get(2)+X.get(3)+X.get(4))+(Y.get(1)+Y.get(5))*(Y.get(2)+Y.get(3)+Y.get(4))==0) return true;

        return false;
    }
}