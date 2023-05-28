import java.math.BigInteger;
import java.util.Scanner;

public class 조합_2407 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        BigInteger answer = BigInteger.ONE;
        for (int i = n - m + 1 ; i <= n; i++) {
            answer = answer.multiply(BigInteger.valueOf(i));
        }
        for (int i = 1; i <= m; i++) {
            answer = answer.divide(BigInteger.valueOf(i));
        }
        System.out.println(answer);
    }
}
