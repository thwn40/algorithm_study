package SeongWoo.week36;

import java.util.Arrays;

public class 입국심사 {

    public long binarySearch(int n, int[] times) {

        long left = 0;
        long right = (long) times[times.length - 1] * n;

        while (left < right) {

            long mid = (right + left) / 2;
            long count = n;
            for (int i = 0; i < times.length; i++) {
                count -= mid / times[i];
            }

            if (count <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public long solution(int n, int[] times) {
        Arrays.sort(times);

        return binarySearch(n, times);
    }
}
