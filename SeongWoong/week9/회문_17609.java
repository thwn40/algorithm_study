import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 회문_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            System.out.println(check(br.readLine(),0));
        }
    }

    public static int check(String str,int cnt) {
        int start = 0;
        int end = str.length()-1;
        while (start < end) {
            if (cnt>=2) {
                return 2;
            }
            if (str.charAt(start) == str.charAt(end)) {
                start++;
                end--;
            } else {
                cnt++;
                int case1 = check(str.substring(start,end),cnt);
                int case2 = check(str.substring(start+1,end+1),cnt);
                cnt = Math.min(case1,case2);
                break;
            }
        }
        if (cnt == 0) {
            return 0;
        } else if (cnt == 1) {
            return 1;
        } else {
            return 2;
        }
    }
}
