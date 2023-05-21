package SeongWoo.week29;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 튜플 {

    public static List<List<Integer>> parsing(String s) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '}' || c == ',') {
                continue;
            }

            List<Integer> list = new ArrayList<>();
            while (c != '}' && c != ',') {
                StringBuilder sb = new StringBuilder();
                while (c != '}' && c != ',') {
                    sb.append(c);
                    i++;
                    c = s.charAt(i);
                }
                list.add(Integer.parseInt(String.valueOf(sb)));
                i++;
                c = s.charAt(i);
            }
            list.sort(Comparator.naturalOrder());
            result.add(list);
        }

        result.sort((l1, l2) -> l1.size() - l2.size());
        return result;
    }

    public int[] solution(String s) {
        List<List<Integer>> parsing = parsing(s);
        int[] answer = new int[parsing.size()];

        answer[0] = parsing.get(0).get(0);

        for (int i = 1; i < parsing.size(); i++) {
            List<Integer> after = parsing.get(i);
            List<Integer> before = parsing.get(i - 1);

            for (int j = 0; j < before.size(); j++) {
                if (!before.get(j).equals(after.get(j))) {
                    answer[i] = after.get(j);
                    break;
                }
                if (j == before.size() - 1) {
                    answer[i] = after.get(after.size() - 1);
                }
            }
        }

        return answer;
    }
}
