package Inhwan.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 크게만들기_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] NK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NK[0], K = NK[1];

        int[] arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int l = arr.length;

        int i = 0, j = 1;

        for (int k = 0; k < K; k++) {
            while (j < l && arr[i] >= arr[j]) {
                i = j;
                j = i+1;
            }

            arr[i] = -1;
            while (i > -1 && arr[i] < 0) i--;
            if (i < 0) {
                i = j;
                j = i+1;
            }
        }

        for (int k = 0; k < l; k++) {
            if (arr[k] >= 0) System.out.print(arr[k]);
        }
    }
}