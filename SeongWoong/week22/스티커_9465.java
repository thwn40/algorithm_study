import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n];
            int[][] answer = new int[2][n];
            for (int c = 0; c < 2; c++) {
                String str = br.readLine();
                StringTokenizer st = new StringTokenizer(str);
                for (int j = 0; j < n; j++) {
                    arr[c][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer[0][0] = arr[0][0];
            answer[1][0] = arr[1][0];
            if (n>1){
                answer[0][1] = answer[1][0]+arr[0][1];
                answer[1][1] = answer[0][0]+arr[1][1];
                for (int j = 2; j < n; j++) {
                    answer[0][j] = Math.max(answer[0][j - 2], Math.max(answer[1][j - 2], answer[1][j - 1]))
                            + arr[0][j];
                    answer[1][j] = Math.max(answer[0][j - 2], Math.max(answer[1][j - 2], answer[0][j - 1]))
                            + arr[1][j];
                }
                int max1 = Math.max(answer[0][n-1],answer[1][n-1]);
                int max2 = Math.max(answer[0][n-2],answer[1][n-2]);
                System.out.println(Math.max(max1, max2));
            }else{
                System.out.println(Math.max(arr[0][0], arr[1][0]));
            }
        }
    }
}
