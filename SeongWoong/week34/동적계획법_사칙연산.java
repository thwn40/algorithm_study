package 코딩테스트연습;

public class 동적계획법_사칙연산 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] arr = new String[]{"5", "-", "3", "+", "1", "+", "2", "-", "4"};
        System.out.println(solution.solution(arr));
    }

    static class Solution {
        public int solution(String arr[]) {
            int max = 0;
            int min = 0;
            int sum = 0;
            for (int i = 0; i < arr.length-1 ; i += 2) {
                int num = Integer.parseInt(arr[arr.length - 1 - i]);
                String pm = arr[arr.length - 2 - i];
                if (pm.equals("+")) sum += num;
                else if (pm.equals("-")) {
                    int temp1 = -(num + sum + min);
                    int temp2 = -num + sum + max;
                    int temp3 = -(num+ sum + max);
                    int temp4 = -(num + sum) + min;
                    max = Math.max(temp1, temp2);
                    min = Math.min(temp3, temp4);
                    sum = 0;
                }
            }
            return max + Integer.parseInt(arr[0]) + sum;
        }
    }
}
