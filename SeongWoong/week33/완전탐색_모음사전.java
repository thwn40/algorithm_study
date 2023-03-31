public class 완전탐색_모음사전 {
    public static void main(String[] args) {
//        String word = "AAAE";
        String word = "U";
        Solution solution = new Solution();
        System.out.println(solution.solution(word));
    }
    static int ans,cnt;
    static class Solution {
        public int solution(String word) {
            find(word, new StringBuilder());
            return ans;
        }
        public static void find(String word,StringBuilder sb){
            if(sb.toString().equals(word)){
                ans = cnt;
                return;
            }
            if(sb.length()==5) return;

            Character[] arr = new Character[]{'A','E','I','O','U'};

            for(int i=0;i<5;i++){
                cnt++;
                sb.append(arr[i]);
                find(word, sb);
                sb.deleteCharAt(sb.length()-1);
                if (ans!=0) return;
            }
        }
    }
}
