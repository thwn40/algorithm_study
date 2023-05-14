import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;
        int sum = 0;
        while (end < N || start < N) {
            if (end >= N) {
                if (sum >= S) {
                    answer = Math.min(answer, end - start);
                }
                sum -= arr[start++];
            } else {
                if (sum < S) {
                    sum += arr[end++];
                } else if (sum >= S) {
                    answer = Math.min(answer, end - start);
                    sum -= arr[start++];
                }
            }
        }
        if (answer == Integer.MAX_VALUE) answer = 0;
        System.out.println(answer);
    }
}
