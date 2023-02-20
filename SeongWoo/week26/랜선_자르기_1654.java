package SeongWoo.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 랜선_자르기_1654 {

    private static long getRan(List<Long> ranList, long number) {
        return ranList.stream()
                .map(ran -> ran / number)
                .reduce(Long::sum)
                .get();
    }

    private static long biSearch(List<Long> ranList, long n) {

        long start = 1;
        long end = ranList.stream()
                .max(Comparator.naturalOrder())
                .get();
        long result = 1;

        while (start <= end) {
            long mid = (start + end) / 2;
            long len = getRan(ranList, mid);

            if (len < n) {
                end = mid - 1;
            } else {
                result = mid;
                start = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long k = Long.parseLong(st.nextToken());
        long n = Long.parseLong(st.nextToken());
        List<Long> ranList = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            long ran = Long.parseLong(st.nextToken());
            ranList.add(ran);
        }

        long result = biSearch(ranList, n);
        System.out.println(result);
    }
}
