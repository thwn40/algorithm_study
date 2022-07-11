package SeongWoong.week1;

import java.util.Scanner;

public class 포도주시식_2156_나의풀이 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // n = 포도잔의 개수
        int n = sc.nextInt();
        // wine[i] = i번째 포도잔에 들어있는 포도주의 양
        int[] wine = new int[n];
        // arr[i][0] = OXX + O 의경우( i-1,i-2 번째 포도잔을 마시고 i번째 포도잔을 마시지 않는다.)
        // arr[i][1] = OX + O 의경우( i-2 번째 포도잔을 마시지 않고 i번째 포도잔을 마신다.)
        // arr[i][2] = XO + O 의경우( i-2 번째 포도잔을 마시지 않고 i번째 포도잔을 마신다.)

        // i-2값이 필요했기 때문에 arr의 인덱스 2번이 wine의 0번 인덱스가 되도록 설정했다.
        int[][] arr = new int[n + 2][3];
        for (int i = 0; i < n; i++) {
            wine[i] = sc.nextInt();
        }
        int sum = 0;
        // arr[0] ,arr[1] 은 계산을 위해 임의로 넣어준 값
        arr[0][0] = arr[0][1] = arr[0][2] = 0;
        arr[1][0] = arr[1][1] = arr[1][2] = 0;
        arr[2][0] = arr[2][1] = arr[2][2] = wine[0];
        for (int i = 3; i < n + 2; i++) {
            // arr[i][0] = i-3까지의 값 중 가장 큰 값에 i-2번째 와인을 넣는다(왜 i-2번째 와인인지는 15번줄에 설명)
            arr[i][0] = Math.max(Math.max(arr[i - 3][1], arr[i - 3][0]), arr[i - 3][2]) + wine[i - 2];
            // arr[i][1] = i-2까지의 값 중 가장 큰 값에 i-2번째 와인을 넣는다
            arr[i][1] = Math.max(Math.max(arr[i - 2][1], arr[i - 2][0]), arr[i - 2][2]) + wine[i - 2];
            // arr[i][2] = i-1까지의 값 중 i-2는 비어있는 두 경우 중 가장 큰 값에 i-2번째 와인을 넣는다
            arr[i][2] = Math.max(arr[i - 1][1], arr[i - 1][0]) + wine[i - 2];
        }
        // 중간에 최대값이 나올 수 도 있기 때문에 모든 경우를 비교해서 출력했다
        for (int i = 1; i < n + 2; i++) {
            sum = Math.max(arr[i][0], sum);
            sum = Math.max(arr[i][1], sum);
            sum = Math.max(arr[i][2], sum);
        }
        System.out.println(sum);
    }
}