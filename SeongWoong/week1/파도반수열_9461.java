package SeongWoong.week1;

import java.util.Scanner;

public class 파도반수열_9461{

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // T = 테스트케이스의 수
        int T = sc.nextInt();
        // pado = 모든 삼각형들의 배열
        // 삼각형 개수 범위는 1~100개
        long[] pado = new long[101];
        // 내가 찾은 규칙 1~5까지는 지정 값
        // 그 이후 부터 i번째 삼각형은 i-1 + i-5
        for(int i=1;i<=100;i++) {
            if (i == 1 || i == 2 || i == 3) {
                pado[i] = 1;
            } else if (i == 4 || i == 5) {
                pado[i] = 2;
            } else {
                if(pado[i]==0){
                    pado[i] = pado[i - 1] + pado[i - 5];
                }
            }
        }
        for (int i = 0; i < T; i++) {
            int num = sc.nextInt();
            System.out.println(pado[num]);
        }
    }
}