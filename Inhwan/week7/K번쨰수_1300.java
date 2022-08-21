package Inhwan.week7;

import java.util.Scanner;

public class K번쨰수_1300 {
    static long N, K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        System.out.println(binarySearch(1,N*N+1));
    }

    static public long binarySearch(long start, long end) {
        if (end-start == 1) {
            return start;
        }

        long middle = (end+start)/2;

        long countUnderMiddle = 0;
        for (long i = 1; i <= N; i++) {
            countUnderMiddle += Math.min((middle-1)/i, N);
            if (countUnderMiddle >= K) break;
        }

        if(countUnderMiddle >= K) return binarySearch(start,middle);
        else return binarySearch(middle, end);
    }
}
