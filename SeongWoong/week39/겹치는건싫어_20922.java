import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 겹치는건싫어_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] cnts = new int[100001];
        int answer = 0;
        int start = 0;
        int end = 0;
        while (end < N) {
            int cur = arr[end];
            cnts[cur]++;
            if (cnts[cur] > K) {
                answer = Math.max(answer, end - start);
                while (cnts[cur] > K) {
                    int c = arr[start++];
                    cnts[c]--;
                }
            }
            end++;
            if (end == N) answer = Math.max(answer, end - start);
        }
        System.out.println(answer);
    }
}
