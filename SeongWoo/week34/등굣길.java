package SeongWoo.week34;

public class 등굣길 {

    static int remain = 1000000007;

    public int dp(int[][] memorization) {

        memorization[0][1] = 1;

        for (int i = 1; i < memorization.length; i++) {
            for (int j = 1; j < memorization[0].length; j++) {
                if (memorization[i][j] == -1) {
                    memorization[i][j] = 0;
                    continue;
                }

                memorization[i][j] = (memorization[i - 1][j] + memorization[i][j - 1]) % remain;
            }
        }

        return memorization[memorization.length - 1][memorization[0].length - 1] % remain;
    }

    public int solution(int m, int n, int[][] puddles) {

        int[][] memorization = new int[n + 1][m + 1];

        for (int i = 0; i < puddles.length; i++) {
            int[] puddle = puddles[i];
            memorization[puddle[1]][puddle[0]] = -1;
        }

        return dp(memorization);
    }
}
