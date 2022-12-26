package SeongWoong.week17;

import java.util.*;

public class 카카오2019_오픈채팅방 {
    public static void main(String[] args) {
        String[] strArr = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(strArr)));
    }

    static class Solution {
        public String[] solution(String[] record) {
            String[] answer = {};

            List<String> tempAnswer = new ArrayList<>();
            HashMap<String, String> map = new HashMap<>();

            for (int i = 0; i < record.length; i++) {
                String command = record[i].split(" ")[0];
                String id = record[i].split(" ")[1];

                if (command.equals("Leave")) {
                    tempAnswer.add(id + " 님이 나갔습니다.");
                    continue;
                }

                String name = record[i].split(" ")[2];

                if (command.equals("Enter")) {
                    tempAnswer.add(id + " 님이 들어왔습니다.");
                    map.put(id, name);
                } else if (command.equals("Change")) {
                    map.put(id, name);
                }
            }

            answer = new String[tempAnswer.size()];
            for (int j = 0; j < tempAnswer.size(); j++) {
                String str = tempAnswer.get(j);
                String id = str.split(" ")[0];
                answer[j] = str.replace(id+" ", map.get(id));
            }

            return answer;
        }
    }
}

