package SeongWoong.week17;

import java.util.ArrayList;
import java.util.Arrays;

public class 카카오2019_후보키_실패 {
    public static void main(String[] args) {
//        String[][] relation = new String[][]{
//                {"100", "ryan", "music", "2"},
//                {"200", "apeach", "math", "2"},
//                {"300", "tube", "computer", "3"},
//                {"400", "con", "computer", "4"},
//                {"500", "muzi", "music", "3"},
//                {"600", "apeach", "music", "2"}};
        String[][] relation = new String[][]{
                {"a", "1", "aaa", "c", "ng"},
                {"a", "1", "bbb", "e", "g"},
                {"c", "1", "aaa", "d", "ng"},
                {"d", "2", "bbb", "d", "ng"}};
        Solution sol = new Solution();
        System.out.println(sol.solution(relation));
    }
    static class Solution {
        public int solution(String[][] relation) {
            int answer = 0;

            String[] temp = new String[relation.length];
            Arrays.fill(temp, "");

//        boolean[] booleans = new boolean[relation[0].length];

            ArrayList<Integer>[] arr = new ArrayList[relation[0].length];

            for (int i = 0; i < relation[0].length; i++) {
                answer += run(relation, temp, arr, i);
            }

            return answer;
        }

        public static int run(String[][] relation, String[] origin, ArrayList<Integer>[] arr, int c) {
            if (c == relation.length) return 0;
            String[] temp = new String[origin.length];

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < origin.length; i++) {
                temp[i] = origin[i];
            }
            int answer = 0;

            boolean can = true;

            for (int j = 0; j < relation.length; j++) {
                String str = relation[j][c];
                temp[j] += "--" + str;
                for (int k = j + 1; k < relation.length; k++) {
                    if (temp[j].equals(temp[k] + "--" + relation[k][c])) {
                        can = false;
                    }
                }
            }
            if (can) {
                answer++;
                list.add(c);
            } else {
                for (int i = c + 1; i < relation[0].length; i++) {
                    if (arr[c].contains(i)) continue;
                    answer += run(relation, temp, arr, i);
                }
            }
            return answer;
        }
    }

}

