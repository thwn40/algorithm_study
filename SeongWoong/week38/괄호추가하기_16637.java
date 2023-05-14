import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 괄호추가하기_16637 {
    static int answer;
    static int N;
    static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        chars = new char[N + 1];
        chars[0] = '+';
        for (int i = 0; i < str.length(); i++) {
            chars[i+1] = str.charAt(i);
        }
        run(0, 0);
        System.out.println(answer);
    }

    public static void run(int sum, int idx) {
        if (idx > N) {
            answer = Math.max(answer, sum);
            return;
        }
        char ope1 = chars[idx];
        int a = chars[idx + 1] - '0';
        // 괄호 미사용 - 앞에서부터 계산
        run(cal(sum, ope1, a), idx + 2);
        // 괄호 사용 - 뒤에서부터 계산
        if (idx+2<=N){
            char ope2 = chars[idx+2];
            int b = chars[idx + 3] - '0';
            run(cal(sum, ope1, cal(a, ope2,b)), idx + 4);
        }
    }

    public static int cal(int a, char ope ,int b) {
        if (ope == '+') return a + b;
        else if (ope == '-') return a - b;
        else return a * b;
    }
}
