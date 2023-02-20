package SeongWoong.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무자르기_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }
        int left = 0;
        int right = max;
        int mid = max / 2;
        while (left < right) {
            long sum = check(arr, mid);
            if (sum == m) {
                break;
            } else if (sum > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
        }
        // 나누어 떨어지지 않는 경우
        if (check(arr, mid) != m) {
            mid -= 1;
        }
        System.out.println(mid);
    }

    public static long check(int[] arr, int mid) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i] - mid;
            if (cur > 0) {
                sum += cur;
            }
        }
        return sum;
    }
}
