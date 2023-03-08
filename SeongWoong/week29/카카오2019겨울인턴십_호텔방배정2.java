import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 카카오2019겨울인턴십_호텔방배정2 {
    public static void main(String[] args) {
        long k = 10;
        long[] room_number = new long[]{1, 3, 4, 1, 3, 1};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(k, room_number)));
        // 누적합 -> 배열 길이가 수용안된다.
        // 이분탐색은 어떨까 -> 마찬가지 배열길이가 안되고 원하는 값이 계속 변함
        // 값을 저장하는게 좋아보임
        // 구현을해보자 -> 효율성 실패
        // 해설 : 해쉬맵을 사용

    }
    static class Solution {
        Map<Long, Long> map;

        public long[] solution(long k, long[] room_number) {
            map = new HashMap<>();
            long[] answer = new long[room_number.length];

            for (int i = 0; i < room_number.length; i++) {
                answer[i] = run(room_number[i]);
            }

            return answer;
        }

        public long run(long c) {
            if (!map.containsKey(c)) {
                map.put(c, c + 1);
                return c;
            } else {
                long next = run(map.get(c));
                map.put(c, next);
                return next;
            }
        }
    }
}

