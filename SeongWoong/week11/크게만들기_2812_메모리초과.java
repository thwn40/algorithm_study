import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 크게만들기_2812_메모리초과 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");

        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        String str = br.readLine();

        int idx = 0;
        while (K != 0) {
            if (str.charAt(idx) - '0' < str.charAt(idx + 1) - '0') {
                str = str.replaceFirst(Character.toString(str.charAt(idx)), "");
                K--;
                idx--;
                if (idx < 0) {
                    idx = 0;
                }
            } else {
                idx++;
            }
        }
        System.out.println(str);
    }
}
