import java.util.*;

public class 카카오2020인턴십_수식최대화 {
    public static void main(String[] args) {
//        String exp = "100-200*300-500+20";
        String exp = "50*6-3*2";
        Solution solution = new Solution();
        System.out.println(solution.solution(exp));
    }
    static class Solution {
        public long solution(String expression) {
            long answer = 0;
            List<String> list = new ArrayList<>();
            Stack<String> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();

            // 문자열로 주어진 수식을 잘라서 사용하기 편하게 리스트에 담는 과정
            for (int i = 0; i < expression.length(); i++) {
                if ('0' <= expression.charAt(i) && expression.charAt(i) <= '9') {
                    sb.append(expression.charAt(i));
                    if (i == expression.length() - 1) {
                        list.add(sb.toString());
                    }
                } else {
                    list.add(sb.toString());
                    list.add(String.valueOf(expression.charAt(i)));
                    sb = new StringBuilder();
                }
            }
            stack = listToStack(list);

            // 연산의 가능 한 모든 경우 1:+ 2:- 3:*
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (i==j) continue;

                    answer = Math.max(answer, Math.abs(find(stack, i, j)));
                }
            }
            return answer;
        }

        // 3가지 연산자 우선 순위에 따른 계산
        // fir: 첫번째로 계산할 연산자 .. sec,third 동일
        public static long find(Stack<String> stack1, int fir, int sec) {
            String s = "";
            int third = 6 - fir - sec;

            List<String> result = cal(fir,stack1);

            // 첫번째 연산자에서 계산이 모두 끝나면 바로 리턴
            if (result.size()==1) return Long.parseLong(result.get(0));
            stack1 = listToStack(result);

            result = cal(sec,stack1);

            if (result.size()==1) return Long.parseLong(result.get(0));
            stack1 = listToStack(result);

            result = cal(third,stack1);

            return Long.parseLong(result.get(0));
        }

        public static Stack<String> listToStack(List<String> list){
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < list.size(); i++) {
                stack.add(list.get(list.size()-i-1));
            }
            return stack;
        }

        // 계산 과정 - 연산자가 일치하면 계산하고 다르면 그대로 리스트에 넣어줌
        public static List<String> cal(int c, Stack<String> stack){
            Stack<String> stack1 = (Stack<String>) stack.clone();
            String s = "";
            if (c == 1) s = "+";
            else if (c == 2) s = "-";
            else if (c == 3) s = "*";

            List<String> list = new ArrayList<>();
            String curNum = stack1.pop();
            while (!stack1.isEmpty()) {
                String curOpe = stack1.pop();
                if (curOpe.equals(s)) {
                    String nextNum = stack1.pop();
                    curNum = operate(s, curNum, nextNum);
                } else {
                    list.add(curNum);
                    list.add(curOpe);
                    curNum = stack1.pop();
                }
                if (stack1.size() == 0) {
                    list.add(curNum);
                }
            }
            return list;
        }

        // 실제 계산
        public static String operate(String s, String n1, String n2) {
            if (s.equals("+")) return String.valueOf(Long.parseLong(n1) + Long.parseLong(n2));
            else if (s.equals("-")) return String.valueOf(Long.parseLong(n1) - Long.parseLong(n2));
            else return String.valueOf(Long.parseLong(n1) * Long.parseLong(n2));
        }
    }
}
