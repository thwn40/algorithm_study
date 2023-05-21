package SeongWoo.week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 야구 {

    static int maxScore = 0;

    public static int calculateInning(int[] batOrder, int[][] scoreInfo) {

        int order = 0;
        int totalScore = 0;

        for (int i = 0; i < scoreInfo.length; i++) {
            int out = 0;
            int[] base = {1, 0, 0, 0};

            while (out < 3) {
                base[0] = 1;
                int score = scoreInfo[i][batOrder[order]];

                if (score == 0) {
                    out++;
                    order = (order + 1) % 9;
                    continue;
                }

                for (int j = 3; j >= 0; j--) {
                    if (j + score >= 4) {
                        totalScore += base[j];
                    } else {
                        base[j + score] = base[j];
                    }
                    base[j] = 0;
                }
                order = (order + 1) % 9;
            }
        }

        return totalScore;
    }

    public static void dfs(int[] batOrder, boolean[] checkArr, int[][] scoreInfoArr, int index) {

        if (index == 3) {
            index += 1;
        }

        if (batOrder.length == index) {
            int tempTotalScore = calculateInning(batOrder, scoreInfoArr);
            if (tempTotalScore > maxScore) {
                maxScore = tempTotalScore;
            }
            return;
        }

        for (int i = 1; i < batOrder.length; i++) {
            if (checkArr[i]) {
                continue;
            }

            batOrder[index] = i;

            checkArr[i] = true;
            dfs(batOrder, checkArr, scoreInfoArr, index + 1);
            checkArr[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        int[][] scoreInfoArr = new int[size][9];

        for (int i = 0; i < scoreInfoArr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < scoreInfoArr[0].length; j++) {
                scoreInfoArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] batOrder = new int[9];
        boolean[] checkArr = new boolean[9];
        checkArr[0] = true;

        dfs(batOrder, checkArr, scoreInfoArr, 0);

        System.out.println(maxScore);
    }
}
