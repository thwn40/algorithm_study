package SeongWoo.week35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 여행경로 {

    static String[] answer;

    public boolean compareStringArr(String[] s1, String[] s2) {

        for (int i = 0; i < s1.length; i++) {
            if (s1[i].compareTo(s2[i]) < 0) {
                return false;
            }

            if (s1[i].compareTo(s2[i]) > 0) {
                return true;
            }
        }

        return false;
    }

    public void dfs(String[] answerArr, Map<String, List<String>> map, String name, int index) {

        String[] cloneAnswerArr = answerArr.clone();
        cloneAnswerArr[index] = name;


        if (index == cloneAnswerArr.length - 1) {
            if (answer == null || compareStringArr(answer, cloneAnswerArr)) {
                answer = cloneAnswerArr;
            }
            return;
        }

        List<String> list = map.get(name);
        for (int i = 0; i < list.size(); i++) {
            String nextName = list.get(i);
            list.remove(i);
            dfs(cloneAnswerArr, map, nextName, index + 1);
            list.add(i, nextName);
        }

    }

    public String[] solution(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<>();
        String[] answerArr = new String[tickets.length + 1];

        for (int i = 0; i < tickets.length; i++) {
            List<String> list = map.computeIfAbsent(tickets[i][0], k -> new ArrayList<>());
            List<String> toList = map.computeIfAbsent(tickets[i][1], k -> new ArrayList<>());
            list.add(tickets[i][1]);
        }

        dfs(answerArr, map, "ICN", 0);

        return answer;
    }
}
