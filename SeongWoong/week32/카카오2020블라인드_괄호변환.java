package SeongWoong.week32;

import java.util.Stack;

public class 카카오2020블라인드_괄호변환 {
    public static void main(String[] args) {
//        String p = "(()())()";
        String p = "()))((()";
        Solution sol = new Solution();
        System.out.println(sol.solution(p));
    }
    static class Solution {
        public String solution(String p) {
            if (check(p)){
                return p;
            } else {
                return dfs(p);
            }
        }

        public static String dfs(String s) {
            // 조건1 : 빈문자열
            if (s.length() == 0) {
                return s;
            }
            // 조건2 : u,v 로 분리
            String u = "";
            String v = "";
            int l = 0;
            int r = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') l++;
                else r++;

                //균형잡힌 문자열일 때 u,v 분리
                if (l == r) {
                    u = s.substring(0, i + 1);
                    if (i < s.length() - 1) {
                        v = s.substring(i + 1, s.length());
                        break;
                    }
                }
            }

            // 조건3 : u가 올바른 괄호문자열인지 체크
            if (check(u)) {
                return u + dfs(v);
            } else {
                // 조건4 : u가 올바른 괄호문자열이 아니면
                String temp = "(" + dfs(v) + ")";
                u = u.substring(1, u.length() - 1);
                u = u.replace("(", "p").replace(")", "(").replace("p", ")");
                temp = temp + u;
                return temp;
            }
        }

        // 올바른 괄호문자열인지 확인하는 메서드
        public static boolean check(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    stack.add(c);
                } else {
                    if (stack.isEmpty()){
                        return false;
                    }
                    stack.pop();
                }
            }
            // 빈 문자열인 경우
            if (!stack.isEmpty()){
                return false;
            }
            return true;
        }
    }
}
