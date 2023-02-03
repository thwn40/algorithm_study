package SeongWoo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 순환_순열_12104 {

    private static String circulator(String target) {
        return new StringBuffer(target)
                .append(target.charAt(0))
                .deleteCharAt(0)
                .toString();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = new StringTokenizer(br.readLine()).nextToken();
        String target = new StringTokenizer(br.readLine()).nextToken();
        int count = 0;

        for (int i = 0; i < target.length(); i++) {
            target = circulator(target);
            if (target.equals(text)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
