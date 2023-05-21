package SeongWoo.week39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 소수의_연속합 {

    public static List<Integer> createPrimeList(int size) {
        List<Integer> primeList = new ArrayList<>();
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

        for (int i = 0; i < prime.length; i++) {
            if (!prime[i]) {
                primeList.add(i);
            }
        }

        return primeList;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int target = Integer.parseInt(st.nextToken());

        int count = 0;
        List<Integer> primeList = createPrimeList(target);

        if (primeList.isEmpty()) {
            System.out.println(0);
            return;
        }

        int sum = primeList.get(0);
        int start = 0;
        int end = 0;

        while (start <= end) {

            if (sum == target) {
                count++;
            }

            if (sum >= target) {
                if (start + 1 > primeList.size() - 1) {
                    break;
                }
                sum -= primeList.get(start);
                start++;
            } else {
                if (end + 1 > primeList.size() - 1) {
                    break;
                }
                end++;
                sum += primeList.get(end);
            }
        }

        System.out.println(count);
    }
}
