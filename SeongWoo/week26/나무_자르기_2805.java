package SeongWoo.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 나무_자르기_2805 {

    private static long getWood(List<Long> woodList, long hight) {
        return woodList.stream()
                .map(wood -> (wood - hight) >= 0 ? (wood - hight) : 0)
                .reduce((wood1, wood2) -> wood1 + wood2)
                .get();
    }

    private static long biSearch(List<Long> woodList, long dist) {

        long start = 0;
        long end = woodList.stream()
                .max(Comparator.naturalOrder())
                .get();
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long len = getWood(woodList, mid);

            if (len < dist) {
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
        long woodLength = Long.parseLong(st.nextToken());
        long dist = Long.parseLong(st.nextToken());
        List<Long> woodList = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < woodLength; i++) {
            long wood = Long.parseLong(st.nextToken());
            woodList.add(wood);
        }

        long result = biSearch(woodList, dist);
        System.out.println(result);
    }
}
