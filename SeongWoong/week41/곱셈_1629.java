import java.util.Scanner;

public class 곱셈_1629 {
    /*
    이 문제를 풀기위해선 지수법칙과 모듈러 성질에 대해 공부해야한다.
    1. 지수 법칙
    A^B + C = A^B * A^C
    2. 모듈러 성질
    (A * B) % C = (A % C * B % C) % C
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        long C = sc.nextLong();
        System.out.println(run(A, B, C));
    }

    public static long run(long A, long B, long C){
        if (B == 1) {   // 지수가 1일 때는 A를 C로 나눠서 그대로 반환
            return A % C;
        }

        long temp = run(A, B / 2, C);

        // 지수가 홀 수이면 밑을 한번 더 곱해서 지수에 +1을 해준다.
        if (B % 2 == 1) {
            return (temp * temp % C) * A % C;
        }
        // 지수가 짝수이면 그대로
        return temp * temp % C;
    }
}
