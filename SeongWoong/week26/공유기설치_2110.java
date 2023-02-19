package SeongWoong.week26;

import java.util.Arrays;
import java.util.Scanner;

public class 공유기설치_2110 {
    static  int N, C;
    static int[] houses;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        C = sc.nextInt();
        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = sc.nextInt();
        }
        Arrays.sort(houses);

        int answer = 1;
        // 사이거리 최소값
        int left = 1;
        // 사이거리 최대값
        int right = houses[N - 1] - houses[0];
        // 사이거리 적정값
        int mid = (left + right) / 2;

        while(left<=right){
            // 사이거리가 가능한지 체크
            if (check(mid)) {   //가능하면
                answer = Math.max(answer, mid); // 더 큰 거리로 갱신
                left = mid + 1;      // 다음 사이거리 테스트
            } else {
                right = mid - 1;    // 다음 사이거리 테스트
            }
            mid = (left + right) / 2;
        }
        System.out.println(answer);
    }

    // 집 사이의 거리가 조건을 만족하는지 확인하는 함수
    public static boolean check(int mid) {
        int cnt = 1;    // 조건을 만족하는 집 수
        int last = houses[0];   // 마지막 으로 선택된 집
        for (int house : houses) {
            if (house - last >= mid) {  // 사이거리가 충분하면
                cnt++;  // 조건 만족하는 집 수 증가
                last = house; // 마지막 집 변경
            }
        }
        return cnt >= C;
    }
}
