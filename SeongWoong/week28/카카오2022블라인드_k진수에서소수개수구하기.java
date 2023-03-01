package SeongWoong.week28;

import java.util.StringTokenizer;

public class 카카오2022블라인드_k진수에서소수개수구하기 {
    public static void main(String[] args) {
        // 8:42~8:55 1차시도 76점
        // 9:05 2차시도 31번라인 수정 86점
        // 9:15 3차시도 1번11번케이스에러 long타입 수정해결
//        int n = 437674;
//        int k = 3;
        int n = 110000;
        int k = 10;
//        int n =100;
//        int k =3;
        System.out.println(solution(n, k));
    }

    public static int solution(int n, int k) {
        int answer = 0;
        String jinsu = Integer.toString(n, k);
        String[] parse = jinsu.split("0");
        StringTokenizer st = new StringTokenizer(jinsu, "0");
        while(st.hasMoreTokens()){
            long c = Long.parseLong(st.nextToken());
            if (check(c)&&c!=1){
                answer++;
            }
        }
        return answer;
    }

    public static boolean check(long n){
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
