package 코딩테스트연습;

public class 동적계획법_정수삼각형 {
    public static void main(String[] args) {
        int[][] triangle = new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        Solution solution = new Solution();
        System.out.println(solution.solution(triangle));
    }

    static class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;
            int[] arr = new int[triangle[triangle.length - 1].length];
            for (int i = 0; i < triangle.length; i++) {
                int[] temp = new int[triangle[triangle.length - 1].length];
                for (int j = 0; j < triangle[i].length; j++) {
                    if (j == 0) {
                        temp[j] = arr[j] + triangle[i][j];
                    } else if (j == arr.length - 1) {
                        temp[j] = arr[j - 1] + triangle[i][j];
                    } else {
                        temp[j] = triangle[i][j] + Math.max(arr[j], arr[j - 1]);
                    }
                }
                arr = temp;
            }
            for (int i = 0; i < arr.length; i++) {
                answer = Math.max(answer, arr[i]);
            }
            return answer;
        }
    }
}
