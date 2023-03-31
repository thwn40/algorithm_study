import java.util.HashMap;
import java.util.Map;

public class 완전탐색_모음사전_진숩변환 {
    public static void main(String[] args) {
//        String word = "AAAE";
        String word = "U";
        Solution solution = new Solution();
        System.out.println(solution.solution(word));
    }

    static class Solution {
        public int solution(String word) {
            Map<Character, Integer> cMap = new HashMap<>();
            cMap.put('A', 0);
            cMap.put('E', 1);
            cMap.put('I', 2);
            cMap.put('O', 3);
            cMap.put('U', 4);
            int[] nums = new int[]{781, 156, 31, 6, 1};
            int answer = word.length();
            for (int i = 0; i < word.length(); i++) {
                char cur = word.charAt(i);
                answer += cMap.get(cur) * nums[i];
            }
            return answer;
        }
    }
}
