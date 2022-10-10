package Inhwan.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class 회의실배정_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] Meetings = new int[N][];

        for (int i = 0; i < N; i++) {
            Meetings[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(Meetings, Comparator.comparingInt(meeting -> meeting[0]));
        Arrays.sort(Meetings, Comparator.comparingInt(meeting -> meeting[1]));
        System.out.println(Arrays.deepToString(Meetings));

        int end = 0;
        int count = 0;

        for (int[] meeting : Meetings) {
            if (end <= meeting[0]) {
                count++;
                end = meeting[1];
            }
        }

        System.out.println(count);
    }
}