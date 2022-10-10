package Inhwan.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) q.add(Integer.parseInt(br.readLine()));

        int m1, m2;
        int result = 0;
        for (int i = 0; i < N-1; i++) {
            m1 = q.poll();
            m2 = q.poll();

            q.add(m1 + m2);
            result += m1 + m2;
        }

        System.out.println(result);
    }
}