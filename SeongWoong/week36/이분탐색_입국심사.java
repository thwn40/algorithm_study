package SeongWoong.week36;

public class 이분탐색_입국심사 {
    public static void main(String[] args) {
        int n = 6;
        int[] times = new int[]{7, 10};
        Solution solution = new Solution();
        System.out.println(solution.solution(n, times));
    }

    static class Solution {
        public long solution(int n, int[] times) {
            long maxTime = 0;
            for (int i = 0; i < times.length; i++) {
                maxTime = Math.max(maxTime, times[i]);
            }
            long max = maxTime * n;
            long min = 1;
            while (max >= min) {
                long mid = (max + min) / 2;
                long sum = 0;
                for (int i = 0; i < times.length; i++) {
                    sum += mid / times[i];
                }
                if (sum >= n) {
                    max = mid - 1;
                } else {
                    min = mid + 1;
                }
            }
            return max + 1;
        }
    }
}
