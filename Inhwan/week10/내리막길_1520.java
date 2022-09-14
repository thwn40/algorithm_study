package Inhwan.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 내리막길_1520 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] MN = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = MN[0], N = MN[1];

        int[][] map = new int[M][];
        for (int i = 0; i < M; i++) map[i] =Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] numOfRoutes = new int[M][];
        int[] temp;
        for (int i = 0; i < M; i++) {
            temp = new int[N];
            Arrays.fill(temp, -1);
            numOfRoutes[i] = temp;
        }
        numOfRoutes[0][0] = 1;

        System.out.println(count(M-1,N-1,numOfRoutes,map));
    }

    static int count(int i, int j, int[][] numOfRoutes, int[][] map) {
        if (numOfRoutes[i][j] > -1) return numOfRoutes[i][j];

        numOfRoutes[i][j] = 0;

        int N = map[0].length, M = map.length;
        int[][] D = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};

        int I, J;
        for (int[] d : D) {
            I = i + d[0];
            J = j + d[1];

            if (I >= 0 && J >= 0 && I < M && J < N && map[i][j] < map[I][J]) {
                numOfRoutes[i][j] += count(I,J,numOfRoutes,map);
            }
        }

        return numOfRoutes[i][j];
    }
}