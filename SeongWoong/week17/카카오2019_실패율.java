package SeongWoong.week17;

import java.util.Arrays;

public class 카카오2019_실패율 {
    class Solution {
        public int[] solution(int N, int[] stages) {
            int[] answer = {};
            int S = stages.length;
            int[] people = new int[N + 2];
            for (int i = 0; i < S; i++) {
                people[stages[i]]++;
            }
            double[] rate = new double[N];
            for (int i = 1; i < people.length - 1; i++) {
                if (people[i] == 0) {
                    rate[i - 1] = 0;
                    continue;
                }
                rate[i - 1] = (double) people[i] / S;
                S -= people[i];
            }
            double[] temp = Arrays.copyOf(rate, rate.length);
            Arrays.sort(rate);
            boolean[] used = new boolean[N + 1];
            answer = new int[N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < temp.length; j++) {
                    if (rate[rate.length - i - 1] == temp[j]) {
                        if (used[j] == false) {
                            answer[i] = j + 1;
                            used[j] = true;
                            break;
                        }
                    }
                }
            }
            return answer;
        }
    }
}
