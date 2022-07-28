package SeongWoo1.week4;

import java.io.*;

public class 약수의합_17425 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long[] sumDivisors = new long[1000000 + 1];
        long[] cumulative = new long[1000000 + 1];

        for (int i = 1; i <= 1000000; i++) {
            for (int j = i; j <= (1000000 / i); j++) {
                if (i == j) {
                    sumDivisors[i * j] += i;
                } else {
                    sumDivisors[i * j] += i + j;
                }
            }
        }

        for (int i = 1; i <= 1000000; i++) {
            cumulative[i] = cumulative[i - 1] + sumDivisors[i];
        }

        int n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(bf.readLine());
            bw.write(cumulative[number] + "\n");
        }

        bw.flush();
    }
}
