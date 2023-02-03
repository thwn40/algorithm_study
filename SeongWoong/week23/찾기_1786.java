package SeongWoong.week23;

import java.util.Scanner;

public class 찾기_1786 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        String P = sc.nextLine();
        int[] pi = setPi(P);

        int[] answer = search(S, P, pi);
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.length; i++) {
            if (answer[i]==1){
                cnt++;
                sb.append(i+1 + " ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);

    }
    static int[] setPi(String P) {
        int pLen = P.length();
        int[] pi = new int[pLen];

        int idx = 0;
        for (int i = 1; i < pLen; i++) {
            // idx가 0보다 크고 char가 일치하지 않는 경우
            while (idx > 0 && P.charAt(i) != P.charAt(idx)) {
                idx = pi[idx - 1];
            }

            // char가 일치하는 경우
            if (P.charAt(i) == P.charAt(idx)) {
                idx++;
                pi[i] = idx;
            }
        }
        return pi;
    }
    static int[] search(String S, String P,int[] pi) {
        int sLen = S.length();
        int pLen = P.length();
        int[] ans = new int[sLen];

        int idx = 0;
        for(int i = 0; i < sLen; i++) {
            // S를 전부 순회하면서 다른 부분이 나오면 다음 순회인덱스를 바꿔줌
            while(idx > 0 && S.charAt(i) != P.charAt(idx)) {
                idx = pi[idx - 1];
            }

            if(S.charAt(i) == P.charAt(idx)) {
                // 끝까지 일치한 경우
                if(idx == pLen - 1) {
                    ans[i-pLen+1] = 1;
                    idx = pi[idx];
                }
                else {
                    idx++;
                }
            }
        }
        return ans;
    }
}
