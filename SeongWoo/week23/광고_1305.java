package SeongWoo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 광고_1305 {

    private static int[] preprocessing(String target) {

        int[] prefixArr = new int[target.length()];
        prefixArr[0] = 0;

        int j = 0;
        for (int i = 1; i < target.length(); i++) {
            while (j > 0 && target.charAt(i) != target.charAt(j)) {
                j = prefixArr[j - 1];
            }

            if (target.charAt(i) == target.charAt(j)) {
                prefixArr[i] = j + 1;
                j++;
            } else {
                prefixArr[i] = 0;
            }
        }

        return prefixArr;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        String target = new StringTokenizer(br.readLine()).nextToken();

        int[] prefixArr = preprocessing(target);
        int result = size - prefixArr[size - 1];

        System.out.println(result);
    }
}
