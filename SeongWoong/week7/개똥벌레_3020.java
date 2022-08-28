import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 개똥벌레_3020 {
    static int N, H;
    static int[] top, bottom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        // 기둥의 갯수
        N = Integer.parseInt(str[0]);
        // 장애물의 최대 높이
        H = Integer.parseInt(str[1]);
        // 석순과 종유석을 담을 배열
        top = new int[N / 2];
        bottom = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            bottom[i] = Integer.parseInt(br.readLine());
            top[i] = Integer.parseInt(br.readLine());
        }
        // 이분 탐색을 위해 정렬
        Arrays.sort(top);
        Arrays.sort(bottom);

        // 부숴야하는 기둥의 최소 갯수
        int min = N;
        // 최소갯수를 만족하는 구간
        int cnt = 0;

        //구간을 모두 돌면서
        for (int i = 1; i <= H; i++) {
            // 이분탐색으로 부숴야하는 최소 갯수를 구한다.
            int sum = search(0, N / 2, i, bottom) + search(0, N / 2, H - i + 1, top);
            if (min == sum) {
                cnt++;
                continue;
            }
            if (min > sum) {
                min = sum;
                cnt = 1;
            }
        }
        System.out.println(min + " " + cnt);
    }

    public static int search(int start, int end, int h, int[] arr) {
        //lower bound를 통해 입력받은 h(단계)를 만족하는 기둥의 최소 인덱스를 구한다.
        while (start < end) {
            int mid = (start + end) / 2;

            if (arr[mid] >= h) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        // 부숴야하는 기둥의 수를 리턴
        return arr.length - end;
    }
}
