import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n][n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i ; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    temp[j] = arr[j] + triangle[i][j];
                } else if (j == n - 1) {
                    temp[j] = arr[j - 1] + triangle[i][j];
                } else {
                    temp[j] = triangle[i][j] + Math.max(arr[j], arr[j - 1]);
                }
            }
            arr = temp;
        }
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, arr[i]);
        }
        System.out.println(answer);
    }
}
