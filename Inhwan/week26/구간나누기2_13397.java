package Inhwan.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 구간나누기2_13397 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nm[0], m = nm[1];
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int s = 0;
        int e = 9999;

        if (test(arr,m, 0)) {
            System.out.println(0);
            return;
        }

        while (e - s > 1) {
            int mid = (s + e)/2;

            if (test(arr, m, mid)) e = mid;
            else s = mid;
        }

        System.out.println(e);
    }

    static boolean test(int[] arr, int m, int t) {

        if (arr.length == 1) return true;

        int count = 1;
        int i = 1;

        int max = arr[0];
        int min = arr[0];

        while (i < arr.length) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);

            if (max - min <= t) {
                i++;
                continue;
            }

            count++;
            max = arr[i];
            min = arr[i];
        }

        return count <= m;
    }
}