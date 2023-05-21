package SeongWoo.week33;

import java.util.HashMap;
import java.util.Map;

public class 모음사전 {
    public int solution(String word) {

        int answer = 0;

        Map<Character, Integer> wordMap = new HashMap<>();
        wordMap.put('A', 0);
        wordMap.put('E', 1);
        wordMap.put('I', 2);
        wordMap.put('O', 3);
        wordMap.put('U', 4);

        int[] weight = {781, 156, 31, 6, 1};

        for (int i = 0; i < word.length(); i++) {
            Integer index = wordMap.get(word.charAt(i));
            answer += 1 + weight[i] * index;
        }

        return answer;
    }
}
