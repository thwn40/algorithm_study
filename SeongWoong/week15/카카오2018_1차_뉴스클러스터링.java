package SeongWoong.week15;

import java.util.*;
public class 카카오2018_1차_뉴스클러스터링 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str1 = "FRANCE";
        String str2 = "french";
        System.out.println(solution.solution(str1,str2));
        // ==> 16384
    }
    static class Solution {
        public int solution(String str1, String str2) {
            int answer = 0;
            double result = 0;
            HashSet<String> hap = new HashSet<>();
            ArrayList<String> list1 = new ArrayList<>();
            ArrayList<String> list2 = new ArrayList<>();
            str1 = str1.toUpperCase();
            str2 = str2.toUpperCase();

            for(int i=0;i<str1.length()-1;i++){
                if(str1.charAt(i)>'Z' || str1.charAt(i)<'A' || str1.charAt(i+1)>'Z' || str1.charAt(i+1)<'A') {
                    continue;
                }
                hap.add(str1.substring(i,i+2));
                list1.add(str1.substring(i,i+2));
            }
            for(int i=0;i<str2.length()-1;i++){
                if(str2.charAt(i)>'Z' || str2.charAt(i)<'A' || str2.charAt(i+1)>'Z' || str2.charAt(i+1)<'A') {
                    continue;
                }
                hap.add(str2.substring(i,i+2));
                list2.add(str2.substring(i,i+2));
            }

            if(list1.size()==0 && list2.size() ==0){
                return 65536;
            }

            int gyoCnt = 0;
            int hapCnt = 0;
            for(String a : hap){
                int A = 0;
                int B = 0;
                for(int i=0;i<list1.size();i++){
                    if(list1.get(i).equals(a)){
                        A++;
                    }
                }
                for(int i=0;i<list2.size();i++){
                    if(list2.get(i).equals(a)){
                        B++;
                    }
                }
                gyoCnt+=Math.min(A,B);
                hapCnt+=Math.max(A,B);
            }


            result = (double)gyoCnt/hapCnt;
            answer = (int)(result * 65536);
            // System.out.println(result);
            // System.out.println(gyoCnt);
            // System.out.println(hapCnt);
            return answer;
        }
    }
}
