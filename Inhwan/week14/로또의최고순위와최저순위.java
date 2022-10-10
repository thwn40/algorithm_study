package Inhwan.week14;

import java.util.HashSet;
import java.util.Set;

class 로또의최고순위와최저순위 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        Set<Integer> set = new HashSet<>();
        for (int num : win_nums) {
            set.add(num);
        }

        int countWinNums = 0;
        int countZeros = 0;

        for (int num : lottos) {
            if (set.contains(num)) {
                countWinNums++;
            } else if (num == 0) {
                countZeros++;
            }
        }

        answer[0] = calculateRank(countWinNums + countZeros);
        answer[1] = calculateRank(countWinNums);

        return answer;
    }

    private int calculateRank(int countWinNums) {
        return Math.min(6, 7-countWinNums);
    }
}