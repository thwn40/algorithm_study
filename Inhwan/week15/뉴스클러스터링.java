package Inhwan.week15;

import java.util.HashMap;
import java.util.Map;

class 뉴스클러스터링 {
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        Map<String, Integer> set1 = new HashMap<>();
        Map<String, Integer> set2 = new HashMap<>();

        addToMap(set1, str1);
        addToMap(set2, str2);

        int a = valueSum(makeIntersection(set1, set2));
        int b = valueSum(makeUnion(set1, set2));

        if (b==0) return 65536;

        int answer = 65536 * a / b;
        return answer;
    }

    void addToMap(Map<String, Integer> set, String str) {
        String subStr;
        char a;
        char b;

        for (int i = 0; i < str.length() - 1; i++) {
            a = str.charAt(i);
            b = str.charAt(i+1);

            if (a < 65 || b < 65 || a > 90 || b > 90) continue;

            subStr = "" + a + b;

            if (set.containsKey(subStr)) {
                set.put(subStr, set.get(subStr) + 1);
            } else {
                set.put(subStr, 1);
            }
        }
    }

    Map<String, Integer> makeUnion(Map<String, Integer> set1, Map<String, Integer> set2) {
        Map<String, Integer> result = new HashMap<>(set1);

        set2.keySet().stream().forEach(key -> {
            if (result.containsKey(key)) {
                result.put(key, Math.max(result.get(key), set2.get(key)));
            } else {
                result.put(key, set2.get(key));
            }
        });

        return result;
    }

    Map<String, Integer> makeIntersection(Map<String, Integer> set1, Map<String, Integer> set2) {
        Map<String, Integer> result = new HashMap<>();

        set2.keySet().stream().forEach(key -> {
            if (set1.containsKey(key)) {
                result.put(key, Math.min(set1.get(key), set2.get(key)));
            }
        });

        return result;
    }

    int valueSum(Map<String, Integer> set) {
        return set.values().stream().mapToInt(Integer::intValue).sum();
    }
}