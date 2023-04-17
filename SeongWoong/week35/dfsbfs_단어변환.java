package 코딩테스트연습;

import java.util.*;

public class dfsbfs_단어변환 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String begin = "hit";
        String target = "cog";
        String[] words = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution.solution(begin, target, words));
    }

    static class Solution {
        public int solution(String begin, String target, String[] words) {
            int answer = 0;
            Queue<Word> q = new PriorityQueue<>();
            q.add(new Word(begin, 0));
            boolean[] visit = new boolean[words.length];
            while (!q.isEmpty()) {
                Word cur = q.poll();
                if (cur.word.equals(target)) {
                    answer = cur.cnt;
                    break;
                }
                for (int i = 0; i < words.length; i++) {
                    if (visit[i]) continue;
                    String s = words[i];
                    int cnt = check(s, cur.word);
                    if (cnt == 1) {
                        q.add(new Word(s, cur.cnt + 1));
                        visit[i] = true;
                    }
                }
            }
            return answer;
        }

        public int check(String word1, String word2) {
            int cnt = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) cnt++;
            }
            return cnt;
        }

        class Word implements Comparable<Word> {
            String word;
            int cnt;

            public Word(String word, int cnt) {
                this.word = word;
                this.cnt = cnt;
            }

            @Override
            public int compareTo(Word o) {
                return this.cnt - o.cnt;
            }
        }
    }
}
