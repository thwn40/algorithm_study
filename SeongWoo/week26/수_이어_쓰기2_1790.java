package SeongWoo.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수_이어_쓰기2_1790 {

    private static long getDigit(int n) {
        long ans = 0;
        for (int start=1, len=1; start<=n; start*=10, len++) {
            int end = start * 10-1;
            if (end > n) {
                end = n;
            }
            ans += (long)(end - start + 1) * len;
        }
        return ans;
    }

    private static int biSearch(int n, int k) {

        int start = 1;
        int end = n;
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            long len = getDigit(mid);
            if (len < k) {
                start = mid + 1;
            } else {
                result = mid;
                end = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long digit = getDigit(n);
        if (k > digit) {
            System.out.println(-1);
            return;
        }

        int result = biSearch(n, k);

        String s = Integer.toString(result);
        long len = getDigit(result);
        System.out.println(s.charAt(s.length() - (int)(len - k) - 1));
    }
}
