package Inhwan.week16;

import java.util.Arrays;

class 자동완성 {
    public int solution(String[] words) {
        Arrays.sort(words);
        int l = words.length;

        int ans = 0;

        ans += common(words[0], words[1]);
        ans += common(words[l-1], words[l-2]);

        for (int i = 1; i < l-1; i++) {
            ans += Math.max(common(words[i], words[i+1]), common(words[i], words[i-1]));
        }

        return ans;
    }

    int common(String str1, String str2) {
        int i = 0;

        while (i < str1.length() &&
                i < str2.length() &&
                str1.charAt(i) == str2.charAt(i)) {
            i++;
        }

        return Math.min(i+1, str1.length());
    }
}