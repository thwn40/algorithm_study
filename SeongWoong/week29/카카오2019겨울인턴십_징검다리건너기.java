import java.util.LinkedList;
import java.util.Queue;

// 실패 - 다시풀어라
public class 카카오2019겨울인턴십_징검다리건너기 {
    public static void main(String[] args) {
        int[] stones = new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        Solution solution = new Solution();
        System.out.println(solution.solution(stones, k));
    }
    static class Solution {
        public int solution(int[] stones, int k) {
            int answer = 0;
            int left = 0;
            int right = 200000000;

            while (left != right) {
                int mid = (left + right) / 2;
                if (can(mid, stones.clone(), k)) {
                    answer = Math.max(answer, mid);
                    left = mid +1 ;
                } else {
                    right = mid;
                }
            }
            return answer;
        }

        public boolean can(int mid, int[] stones, int k) {
            int cnt = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] - mid < 0) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                if (cnt == k) {
                    return false;
                }
            }
            return true;
        }
    }
}

