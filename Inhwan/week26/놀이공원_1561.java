package Inhwan.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 놀이공원_1561 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nm[0], m = nm[1];
        long N = n;

        int[] times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (n <= m) {
            System.out.println(n);
            return;
        }

        long s = 0;
        
        long e = N * 30;

        while (e - s > 1) {
            long mid = (e + s) / 2;

            if (count(times, mid) >= n) e = mid;
            else s = mid;
        }

        long dif = n - count(times, s);

        for (int i = 0; i < m; i++) {
            if (e % times[i] == 0) {
                dif--;

                if (dif == 0) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }

    static long count(int[] times, long time) {

        long result = 0;

        for (int t : times) {
            result += time / t + 1;
        }

        return result;
    }
}