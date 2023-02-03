package SeongWoo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 찾기_1786 {

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

    private static List<Integer> kmp(String text, String target) {

        List<Integer> result = new ArrayList<>();
        int[] prefixArr = preprocessing(target);
        int dist = target.length();

        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target.charAt(j)) {
                j++;
                if (j == dist) {
                    result.add(i - j + 2);
                    j = prefixArr[j - 1];
                }
            } else if (j > 0) {
                j = prefixArr[j - 1];
                i--;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text =  new StringTokenizer(br.readLine(), "").nextToken();
        String target =  new StringTokenizer(br.readLine(), "").nextToken();

        List<Integer> result = kmp(text, target);

        System.out.println(result.size());
        for (Integer integer : result) {
            System.out.print(integer);
            System.out.print(" ");
        }
    }
}
