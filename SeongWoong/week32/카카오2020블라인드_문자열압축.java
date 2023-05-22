package SeongWoong.week32;

import java.util.ArrayList;
import java.util.List;

public class 카카오2020블라인드_문자열압축 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aabbaccc";
        System.out.println(solution.solution(s));
    }
    static class Solution {
        public int solution(String s) {
            int answer = s.length();
            for (int i = 1; i <= s.length()/2; i++) { // i는 문자열 압축 정도
                String cur = s.substring(0, i); // cur는 첫번째 확인할 부분문자열
                int cnt = 1; //몇개 있는가
                StringBuilder sb = new StringBuilder(); // 압축된 문자열
                for (int j = i; j <= s.length(); j+=i) {
                    String next = "";
                    if (j + i > s.length()) {   // 남아있는 문자열이 부분문자열보다 짧을 경우
                        next = s.substring(j, s.length());
                    } else {
                        next = s.substring(j, j + i);
                    }
                    if (cur.equals(next)) { // 같으면 갯수 증가
                        cnt++;
                    } else {
                        if (cnt != 1) { //갯수가 1이 아니면 갯수를 새 문자열에 적어줌
                            sb.append(cnt);
                        }
                        sb.append(cur); // 부분문자열도 적어줌
                        cur = next; // 다음 문자를 보기위해 다음문자열을 현재문자열로 바꿔줌
                        cnt = 1; // 갯수 초기화
                    }
                }
                sb.append(cur); // 남은 문자열을 sb에 추가
                answer = Math.min(sb.length(), answer);
            }
            return answer;
        }

    }
}
