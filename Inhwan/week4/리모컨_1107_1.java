// 고장나지 않은 버튼으로 이동할 수 있는 채널들을 탐색하는 방법

import java.util.*;

public class 리모컨_1107_1 {
    // remote 메서드에서 사용하기 위해 로컬 변수가 아닌 클래스 변수로 선언
    static int ans,N,M;
    static boolean[] unbroken = new boolean[10];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        // 숫자 버튼을 사용하지 않고 이동할 때 버튼을 눌러야하는 횟수
        ans = Math.abs((N-100));

        // unbroken -> 고장나지 않은 버튼을 나타내는 배열
        Arrays.fill(unbroken,true);
        for (int i = 0; i<M; i++) unbroken[sc.nextInt()]=false;

        // 재귀함수를 이용해 최소값을 계산후 출력
        remote("");
        System.out.println(ans);
    }
    // 숫자버튼으로 이동할 수 있는 채널번호가 문자열 형태로 입력
    // 입력된 채널번호가 N 이하일 경우 채널번호 뒤에 숫자를 붙여 재귀적으로 반복
    static void remote(String str) {

        if (str.length() > 0) {
            int n = Integer.parseInt(str);

            // str의 길이가 N의 길이 - 1 이상일 때부터 계산
            if (str.length() > String.valueOf(N).length() - 2) {
                ans = Math.min(ans, Math.abs(N - n) + String.valueOf(n).length());
            }

            // 0 버튼이 고장나지 않았을 경우, 0이 무한대로 늘어나는 경우 방지
            // n이 N 이상이면 종료
            if (str.length() > String.valueOf(n).length() + 1 || n >= N) return;
        }

        for (int i = 0; i < 10; i++) if (unbroken[i]) remote(str + (char) (i + '0'));
    }
}

