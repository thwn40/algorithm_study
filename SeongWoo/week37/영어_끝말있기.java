package SeongWoo.week37;

import java.util.HashMap;
import java.util.Map;

public class 영어_끝말있기 {

    public boolean checkWord(String beforeWord, String afterWord) {
        if (beforeWord.charAt(beforeWord.length() - 1) != afterWord.charAt(0)) {
            return false;
        }
        return true;
    }

    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        Map<String, Integer> wordMap = new HashMap<>();
        wordMap.put(words[0], 1);

        for (int i = 1; i < words.length; i++) {
            if (wordMap.containsKey(words[i]) || !checkWord(words[i - 1], words[i])) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;

                return answer;
            }

            wordMap.put(words[i], 1);
        }

        return answer;
    }
}
