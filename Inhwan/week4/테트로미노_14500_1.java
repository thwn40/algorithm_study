import java.io.*;
import java.util.*;

public class 테트로미노_14500_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = nm[0]; m = nm[1];
        board = new int[n][];
        for (int i = 0; i < n; i++) board[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        sum = 0; M = 0;
        C= new boolean[7][7];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f(i,j,0,0,0);
            }
        }

        System.out.println(M);
    }
    static int M, sum,n,m;
    static int[][] D1= {{0,1},{1,0},{0,-1},{-1,0}}, D2 = {{-1,1},{-1,-1},{1,0}}, D3 = {{1,-1},{-1,-1},{1,0}};
    static int[][] board;
    static boolean[][] C;


    static void f(int x,int y,int c,int dx,int dy) {
        C[trans(dx)][trans(dy)]=true;
        sum+=board[x+dx][y+dy];

        if (c==3) {
            M= Math.max(M,sum);
            C[trans(dx)][trans(dy)]=false;
            sum-=board[x+dx][y+dy];
            return;
        }

        int[][] D=D1;
        if (dx==2) D=D2;
        if (dy==2) D=D3;

        for (int[] d : D) {
            int ddx = dx+d[0];
            int ddy = dy+d[1];
            if (!C[trans(ddx)][trans(ddy)] && x+ddx>=0 && y+ddy>=0 && x+ddx<n && y+ddy<m) {
                f(x, y, c+1, ddx, ddy);
            }
        }

        sum-=board[x+dx][y+dy];
        C[trans(dx)][trans(dy)]=false;
    }

    static int trans(int i){
        if (i<0) return 7+i;
        return i;
    }
}