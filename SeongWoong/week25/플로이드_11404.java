import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 플로이드_11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    answer[i][j] = 0;
                } else {
                    answer[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        // 입력
        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0]) - 1;
            int to = Integer.parseInt(str[1]) - 1;
            int cost = Integer.parseInt(str[2]);
            answer[from][to] = Math.min(answer[from][to], cost);
        }
        // 탐색 후 갱신
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (answer[j][i] != Integer.MAX_VALUE && answer[i][k] != Integer.MAX_VALUE) {
                        answer[j][k] = Math.min(answer[j][k], answer[j][i] + answer[i][k]);
                    }
                }
            }
        }
        // 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (answer[i][j] == Integer.MAX_VALUE) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(answer[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
