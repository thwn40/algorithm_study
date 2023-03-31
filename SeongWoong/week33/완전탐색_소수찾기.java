import java.util.HashSet;
import java.util.Set;

public class 완전탐색_소수찾기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("011"));
    }

    static class Solution {
        static int answer;
        static int[] primeNum;
        static boolean[] visit;
        static Set set;

        public int solution(String numbers) {
            // 완전탐색dfs로 모든 수 구하고 각 수가 소수인지 아닌지 확인
            answer = 0;
            int num = 9999999;
            primeNum = getPrime(num);
            visit = new boolean[numbers.length()];
            set = new HashSet<Integer>();
            dfs(numbers, 0, numbers.length(), new StringBuilder());

            return set.size();
        }

        public static void dfs(String s, int cur, int len, StringBuilder sb) {
            if (cur > len) return;
            if (cur != 0) {
                int num = Integer.parseInt(sb.toString());
                if (primeNum[num] == 0) {
                    set.add(num);
                }
            }

            for (int i = 0; i < s.length(); i++) {
                if (visit[i]) continue;

                visit[i] = true;
                sb.append(s.charAt(i));
                dfs(s, cur + 1, len, sb);
                sb.deleteCharAt(sb.length() - 1);
                visit[i] = false;
            }
        }

        public static int[] getPrime(int num) {
            int[] primeNum = new int[10000000];
            primeNum[0] = 1;
            primeNum[1] = 1;

            for (int i = 2; i < Math.sqrt(num); i++) {
                if (primeNum[i] == 1) continue;
                for (int j = i * i; j <= num; j += i) {
                    primeNum[j] = 1;
                }
            }

            return primeNum;
        }
    }
}
