package SeongWoong.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 랜선자르기_1654{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        //이미 가지고있는 랜선의 개수
        int K = Integer.parseInt(st.nextToken());
        //필요한 랜선의 개수
        int N = Integer.parseInt(st.nextToken());
        //가지고 있는 랜선 정보
        int[] wlan = new int[K];
        // 만들 랜선의 최대 길이
        // 초기값 입력과 동시에 비교해서 최대값을 넣는다
        long Max = 0;
        for (int i = 0; i < K; i++) {
            wlan[i] = Integer.parseInt(br.readLine());
            Max = Math.max(Max, wlan[i]);
        }
        //여기까지 입력 받아오기
        Max ++;
        // 만들 랜선의 최소 길이
        long min = 0;
        long mid = 0;
        // 각각 랜선을 잘라서 나온 개수를 sum에 더하는데
        // 개수가 원하는 개수에 도달할 때까지 반복
        // 이분탐색을 이용하여 범위를 절반씩 나누면서 찾아보기
        // ex 902 -> 451 -> 225 -> 112 -> 작아졌으니 168 -> 191 -> 208 -> 199
        while (min < Max) {
            mid = (Max + min) / 2;
            long sum = 0;
            for (int i = 0; i < K; i++) {
                sum += (wlan[i] / mid);
            }
            if (sum < N) {
                Max = mid;
            } else {
                min = mid+1;
            }

        }
        System.out.println(min-1);
    }
}
