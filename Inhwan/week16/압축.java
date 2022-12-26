package Inhwan.week16;

import java.util.ArrayList;
import java.util.List;

class 압축 {
    public int[] solution(String msg) {
        List<Integer> ans = new ArrayList<>();

        String[] arr = "_ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        List<String> dic = new ArrayList<>();
        for (String str : arr) dic.add(str);

        char[] word = msg.toCharArray();
        int i = 0;
        int j = 0;

        while (j < word.length) {
            String str = "";

            while (j < word.length &&
                    dic.contains(str+word[j])) {
                str += word[j];
                j++;
            }

            ans.add(dic.indexOf(str));
            i = j;

            if (j < word.length) {
                dic.add(str+word[j]);
            }
        }

        int[] answer = new int[ans.size()];
        for (int k = 0; k < answer.length; k++) {
            answer[k] = ans.get(k);
        }

        return answer;
    }
}