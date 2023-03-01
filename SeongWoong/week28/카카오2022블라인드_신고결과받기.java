package SeongWoong.week28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class 카카오2022블라인드_신고결과받기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] idList = new String[]{"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int[] answer = sol.solution(idList, report, 2);
        System.out.println(Arrays.toString(answer));
        // 예전에 품
    }
    static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i < id_list.length; i++) {
                map.put(id_list[i], i);
            }

            int[] answer = new int[id_list.length];

            ArrayList<HashSet<String>> setList = new ArrayList<>();
            for(int i=0;i<id_list.length;i++){
                HashSet<String> set = new HashSet<>();
                setList.add(set);
            }

            for(int i=0;i<report.length;i++){
                String[] str = report[i].split(" ");

                setList.get(map.get(str[1])).add(str[0]);
            }

            for(int i=0;i<setList.size();i++){
                if(setList.get(i).size()>=k){
                    for(String a : setList.get(i)){
                        answer[map.get(a)]++;
                    }
                }
            }

            return answer;
        }
    }
}
