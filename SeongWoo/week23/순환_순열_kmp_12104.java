package SeongWoo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 순환_순열_kmp_12104 {

    private static int[] preprocessing(String target) {

        int[] prefixArr = new int[target.length()];
        prefixArr[0] = 0;

        int j = 0;
        for (int i = 1; i < target.length(); i++) {
            while (j > 0 && target.charAt(i) != target.charAt(i)) {
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

    private static int kmp(String text, String target) {

        int count = 0;
        int[] prefixArr = preprocessing(target);

        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != target.charAt(j)) {
                j = prefixArr[j - 1];
            }

            if (text.charAt(i) == target.charAt(j)) {
                j++;
                if (j == target.length()) {
                    count++;
                    j = prefixArr[j - 1];
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = new StringTokenizer(br.readLine()).nextToken();
        String text = new StringTokenizer(br.readLine()).nextToken();

        text = new StringBuffer(text)
                .append(text)
                .deleteCharAt(text.length() * 2 - 1).toString();

        int result = kmp(text, target);

        System.out.println(result);
    }
}
