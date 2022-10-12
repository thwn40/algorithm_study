package SeongJun.week11;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 카드정렬하기_1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int totalCompare = 0;
        int tempCompare = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) queue.add(sc.nextInt());


        while (queue.size() > 1) {
            Integer cardDummy1 = queue.poll();
            Integer cardDummy2 = queue.poll();
            tempCompare = cardDummy1 + cardDummy2;
            queue.add(tempCompare);
            totalCompare += tempCompare;
        }

        System.out.println(totalCompare);

    }
}
