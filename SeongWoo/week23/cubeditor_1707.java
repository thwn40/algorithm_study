package SeongWoo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cubeditor_1707 {

    private static int preprocessing(String target) {

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

        return Arrays.stream(prefixArr).max().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = new StringTokenizer(br.readLine()).nextToken();
        int max = 0;

        for (int i = 0; i < text.length(); i++) {
            String subText = text.substring(i);
            int tempMax = preprocessing(subText);
            if (max < tempMax) {
                max = tempMax;
            }
        }

        System.out.println(max);
    }
}
