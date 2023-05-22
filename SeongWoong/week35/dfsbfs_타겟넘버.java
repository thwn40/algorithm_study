package SeongWoong.week35;

public class dfsbfs_타겟넘버 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] numbers = new int[]{1, 1, 1, 1, 1};
//        int target = 3;
        int[] numbers = new int[]{4, 1, 2, 1};
        int target = 4;
        System.out.println(solution.solution(numbers,target));
    }
    static class Solution {
        static int cnt = 0;
        static int t;
        static int[] nums;
        public int solution(int[] numbers, int target) {
            t = target;
            nums = numbers;
            dfs(0,0);
            return cnt;
        }
        public static void dfs(int cur, int sum){
            if (cur == nums.length) {
                if (sum == t) {
                    cnt++;
                }
                return;
            }
            dfs(cur + 1, sum + nums[cur]);
            dfs(cur + 1, sum - nums[cur]);
        }
    }
}
