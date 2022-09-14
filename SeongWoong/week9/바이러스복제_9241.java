import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 바이러스복제_9241 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();

        int start = 0;
        int rightA = A.length()-1;
        int rightB = B.length()-1;

        while (start < A.length() && start < B.length()) {
            if (A.charAt(start) == B.charAt(start)) {
                start++;
                continue;
            }
            break;
        }
//        int leftCnt = start + 1;
        int end = 0;
        while (rightA >=start && rightB >=start) {
            if (A.charAt(rightA) == B.charAt(rightB)) {
                rightA--;
                rightB--;
                end++;
                continue;
            }
            break;
        }
        System.out.println(B.length() - start - end);
    }
}
