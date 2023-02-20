import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 경로찾기_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] inputNum = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(inputNum[j]);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    //j-i 와 i-k 가 연결되어있으면 j-k도 연결되어있음
                    if (graph[j][i] == 1 && graph[i][k] == 1) {
                        graph[j][k] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N; j++) {
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }
}
