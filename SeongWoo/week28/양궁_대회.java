package SeongWoo.week28;

import java.util.PriorityQueue;

public class 양궁_대회 {

    private static class Score {
        int number;
        int requiredShot;
        int oneShotEfficiency;

        public Score(int number, int requiredShot) {
            this.number = number;
            this.requiredShot = requiredShot;
            if (requiredShot == 1) {
                oneShotEfficiency = number;
            } else {
                oneShotEfficiency = (number * 2) / requiredShot;
            }
        }
    }

    private static boolean calculateScore(int[] apeach, int[] rion) {
        int apeachScore = 0;
        int rionScore = 0;
        for (int i = 0; i < apeach.length; i++) {
            if (rion[i] > apeach[i]) {
                rionScore += 10 - i;
            } else if (apeach[i] >= rion[i] && apeach[i] != 0) {
                apeachScore += 10 - i;
            }
        }

        if (apeachScore >= rionScore) {
            return false;
        }
        return true;
    }

    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        int arrow = n;
        PriorityQueue<Score> queue = new PriorityQueue<>((s1, s2) -> s2.oneShotEfficiency - s1.oneShotEfficiency);

        for (int i = 0; i < info.length; i++) {
            queue.offer(new Score(10 - i, info[i] + 1));
        }

        while (!queue.isEmpty()) {
            Score score = queue.poll();
            if (score.requiredShot > arrow) {
                continue;
            }
            answer[10 - score.number] = score.requiredShot;
            arrow -= score.requiredShot;
        }
        if (arrow > 0) {
            answer[10] = arrow;
        }

        if (!calculateScore(info, answer)) {
            return new int[]{-1};
        }

        return answer;
    }
}
