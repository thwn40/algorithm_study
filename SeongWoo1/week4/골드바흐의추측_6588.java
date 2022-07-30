package SeongWoo1.week4;

import java.io.*;

public class 골드바흐의추측_6588 {

    public static boolean[] makePrimeSet(int size) {
        boolean[] prime = new boolean[size + 1];
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i * i <= size; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= size; j += i) {
                    if (!prime[j]) {
                        prime[j] = true;
                    }
                }
            }
        }

        return prime;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] prime = makePrimeSet(1000000);

        int number;
        while ((number = Integer.parseInt(br.readLine())) != 0){
            boolean flag = false;
            for (int i = 0; i * 2 <= number; i++) {
                if (prime[i]) {
                    continue;
                }

                int tempNumber = number - i;
                if (!prime[tempNumber]) {
                    bw.write(String.format("%d = %d + %d\n", number, i, tempNumber));
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                bw.write("Goldbach's conjecture is wrong.\n");
            }
        }

        bw.flush();
    }
}
