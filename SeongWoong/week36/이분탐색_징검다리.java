package SeongWoong.week36;

import java.util.Arrays;

public class 이분탐색_징검다리 {
    public static void main(String[] args) {
        int dis = 25;
        int[] rocks = new int[]{2, 14, 11, 21, 17};
        int n = 2;
        Solution solution = new Solution();
        System.out.println(solution.solution(dis, rocks, n));
    }
    /*
    거리의 범위가 1~10억
    엄청 큰 숫자 -> 이분탐색
    어떤 값으로 탐색을 진행할 것인가? -> 최솟값중에 가장 큰 거리값
    우선 정렬
    거리값을 이분탐색으로 갱신해가면서
    모든 돌에서 해당 거리값을 구하기위해 제거해야하는 돌의 개수를 구한다
    돌의 개수가 n과 같아질 때까지 반복한다.
     */

    static class Solution {
        public int solution(int distance, int[] rocks, int n) {
            int answer = 0;
            Arrays.sort(rocks);

            int min = 0;
            int max = distance;

            while (max >= min) {
                int mid = (max + min) / 2;
                int cnt = 0;
                int prev = 0;

                for (int i = 0; i < rocks.length; i++) {
                    if (rocks[i] - prev < mid) {
                        cnt++;
                    } else {
                        prev = rocks[i];
                    }
                }
                if (distance - rocks[rocks.length - 1] < mid) {
                    cnt++;
                }
                if (cnt <= n) {
                    answer = mid;
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            }
            return answer;
        }
    }
}
