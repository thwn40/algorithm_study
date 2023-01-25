package SeongWoong.week23;

import java.util.Scanner;

public class 광고_1305 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        sc.nextLine();
        String S = sc.nextLine();
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
        }
        System.out.println(L - pi[L-1]);
    }
}
