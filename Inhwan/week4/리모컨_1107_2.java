package Inhwan.week4;


// 목표 채널로부터 하나씩 이동하며 숫자버튼으로 이동할 수 있는 채널인지 확인하는 방법

import java.util.*;

public class 리모컨_1107_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 고장난 버튼
        boolean[] broken = new boolean[10];
        for (int i = 0; i < M; i++) broken[sc.nextInt()] = true;

        // 숫자 버튼을 사욜하지 않을 때 버튼을 눌러야하는 횟수
        int ans = Math.abs(N - 100);

        // 목표 채널 에서 ans 이상 떨어질 경우 버튼을 눌러야하는 횟수가 ans 이상이므로 최소가 될 수 없음
        for (int i = 0; i <ans ; i++) {
            String str;
            int l;
            boolean det;

            // N-i 먼저 탐색 -> 자리수가 달라질 수 있기 때문! (N-i의 자리수 <= N+i의 자리수)
            if (N>=i) { // 음수인 경우 방지
                str = String.valueOf(N - i);
                l = str.length();
                det = true; // 숫자 버튼으로 이동할 수 있는 채널인지 확인
                for (int j = 0; j < l; j++) {
                    if (broken[str.charAt(j) - '0']) {
                        det = false;    // 하나라도 부서진 버튼이 있으면 패스
                        break;
                    }
                }

                // 숫자 버튼으로 이동할 수 있는 채널이면 총 이동 횟수를 계산하고 ans와 비교해 출력
                // 목표 채널에서부터 탐색했기 때문에 맨처음 발견한 채널이 최소가 됨 -> for문 종료
                if (det) {
                    ans = Math.min(ans,i+l);
                    break;
                }
            }

            // 같은 방식으로 N+i 탐색
            str = String.valueOf(N + i);
            l = str.length();
            det = true;
            for (int j = 0; j < l; j++) {
                if (broken[str.charAt(j)-'0']){
                    det = false;
                    break;
                }
            }
            if (det) {
                ans = Math.min(ans,l+i);
                break;
            }
        }

        System.out.println(ans);
    }
}
