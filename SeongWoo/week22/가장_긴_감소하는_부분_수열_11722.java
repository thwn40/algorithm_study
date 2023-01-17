package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장_긴_감소하는_부분_수열_11722 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        int[] numberArr = new int[size + 1];
        int[] memorization = new int[size + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= size; i++) {
            numberArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= size; i++) {
            memorization[i] = 1;
            for (int j = 1; j <= i; j++) {
                if (numberArr[i] < numberArr[j] && memorization[i] < memorization[j] + 1) {
                    memorization[i] = memorization[j] + 1;
                }
            }
        }

        int result = Arrays.stream(memorization).max().getAsInt();

        System.out.println(result);
    }
}
