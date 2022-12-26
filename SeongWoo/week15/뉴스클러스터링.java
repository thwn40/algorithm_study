package SeongWoo.week15;

import java.util.ArrayList;
import java.util.List;

public class 뉴스클러스터링 {
    public int solution(String str1, String str2) {
        String firstStr = str1.toLowerCase();
        String secondStr = str2.toLowerCase();

        List<String> first = depart(firstStr);
        List<String> second = depart(secondStr);

        float intersection = intersection(first, second);
        float union = union(first, second, intersection);

        if (union == 0) {
            return 1 * 65536;
        }
        return (int) ((intersection / union) * 65536);
    }

    private float intersection(List<String> first, List<String> second) {
        ArrayList<String> copySecond = new ArrayList<>(second);
        float count = 0;
        for (int i = 0; i < first.size(); i++) {
            String str = first.get(i);
            if (!copySecond.contains(str)) {
                continue;
            }
            copySecond.remove(str);
            count++;
        }
        return count;
    }

    private float union(List<String> first, List<String> second, float intersection) {
        return first.size() + second.size() - intersection;
    }

    private List<String> depart(String str) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < str.length() - 1; i++) {
            char firstChar = str.charAt(i);
            char secondChar = str.charAt(i + 1);

            if (('a' > firstChar || 'z' < firstChar) || ('a' > secondChar || 'z' < secondChar)) {
                continue;
            }

            result.add(str.substring(i, i + 2));
        }
        return result;
    }
}
