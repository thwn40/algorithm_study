import java.util.Arrays;
import java.util.Scanner;

public class 쉬운계단수_10844 {
    public static void main(String[] args) {
        // 풀이 참조함..
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[][] arr = new long[N + 1][10];
        Arrays.fill(arr[1],1);
        arr[1][0] = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j-1>=0){
                    arr[i][j] += arr[i - 1][j - 1];
                }
                if (j+1<=9){
                    arr[i][j] += arr[i - 1][j + 1];
                }
                arr[i][j]%=1000000000;
            }
        }
        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += arr[N][i]%=1000000000;
        }
        System.out.println(answer);
    }
}
