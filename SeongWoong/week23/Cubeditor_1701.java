package SeongWoong.week23;

import java.util.Scanner;

public class Cubeditor_1701 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();


        int max = 0;
        while (S.length() > 0) {
            int[] pi = new int[S.length()];
            int idx = 0;
            for (int i = 1; i < S.length(); i++) {
                while (idx > 0 && S.charAt(i) != S.charAt(idx)) {
                    idx = pi[idx - 1];
                }
                if (S.charAt(i) == S.charAt(idx)) {
                    idx++;
                    pi[i] = idx;
                }
                max = Math.max(max, idx);
            }
            S = S.substring(1);
        }
        System.out.println(max);
    }
}
