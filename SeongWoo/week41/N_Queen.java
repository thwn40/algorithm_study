package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_Queen {

    static int count = 0;

    public static boolean judgeRow(int[] colArr, int row, int col) {

        int i = 0;
        while (i < row) {
            if (colArr[i] == col || Math.abs(colArr[i] - col) == (row - i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void dfs(int[] colArr, int row) {

        if (row == colArr.length) {
            count++;
            return;
        }

        for (int col = 0; col < colArr.length; col++) {
            if (!judgeRow(colArr, row, col)) {
                continue;
            }
            colArr[row] = col;
            dfs(colArr, row + 1);
            colArr[row] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        int[] colArr = new int[size];

        dfs(colArr, 0);

        System.out.println(count);
    }
}
