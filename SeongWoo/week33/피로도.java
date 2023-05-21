package SeongWoo.week33;

public class 피로도 {

    static int maxDungeonsCount = 0;

    public void getDungeonsCount(int[][] dungeons, boolean[] check, int remainFatigue, int depth, int index, int count) {

        if (remainFatigue < dungeons[index][0]) {
            if (maxDungeonsCount < count) {
                maxDungeonsCount = count;
            }
            return;
        }

        boolean[] currentCheck = check.clone();
        currentCheck[index] = true;
        remainFatigue -= dungeons[index][1];
        count++;

        if (depth == dungeons.length - 1) {
            maxDungeonsCount = count;
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (currentCheck[i]) {
                continue;
            }
            getDungeonsCount(dungeons, currentCheck, remainFatigue, depth + 1, i, count);
        }
    }

    public int solution(int k, int[][] dungeons) {
        boolean[] check = new boolean[dungeons.length];

        for (int i = 0; i < dungeons.length; i++) {
            if (k < dungeons[i][0]) {
                continue;
            }
            getDungeonsCount(dungeons, check, k, 0, i, 0);
        }

        return maxDungeonsCount;
    }
}
