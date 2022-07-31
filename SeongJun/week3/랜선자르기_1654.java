package SeongJun.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 랜선자르기_1654 {

    public static void main(String[] args) throws IOException, InterruptedException {



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long K = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        ArrayList<Long> arr = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            arr.add(Long.parseLong(br.readLine()));
        }
        //제일 큰거부터
        Collections.sort(arr);
        Long current = arr.get(arr.size()-1);

        long sum = 0;
        long rangeStart = 0;
        long rangeEnd = current;

        while (true) {

            System.out.println("sum = " + sum);

            sum = 0;
            System.out.println("rangeStart = " + rangeStart);
            System.out.println("current = " + current);
            System.out.println("rangeEnd = " + rangeEnd);



            for (int i = 0; i < K; i++) {

                sum += arr.get(i) / current;
            }
            if (sum >= M) {
                rangeStart = current;
            }
            if (sum < M) {
                rangeEnd = current;
            }

            current = (rangeStart + rangeEnd) / 2;


            if(rangeStart==current) {
                System.out.println("rangeStart = " + rangeStart);
                System.out.println("current = " + current);
                System.out.println("rangeEnd = " + rangeEnd);
                System.out.println("sum = " + sum);
                break;
            }


        }

        System.out.println(current);
    }

}
