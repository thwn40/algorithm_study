package SeongWoo.week34;

import java.util.Arrays;

public class 정수_삼각형 {
    public int solution(int[][] triangle) {

        int[][] memorization = new int[triangle.length][triangle.length + 2];
        memorization[0][1] = triangle[0][0];

        for (int i = 1; i < memorization.length; i++) {
            for (int j = 1; j <= triangle[i].length; j++) {
                memorization[i][j] =
                        Math.max(memorization[i - 1][j - 1], memorization[i - 1][j]) + triangle[i][j - 1];
            }
        }

        return Arrays.stream(memorization[memorization.length - 1])
                .max()
                .getAsInt();
    }
}
