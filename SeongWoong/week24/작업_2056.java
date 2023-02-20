import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 작업_2056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] ind = new int[N + 1];
        int[] cost = new int[N + 1];
        int[] answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            String[] str = br.readLine().split(" ");
            cost[i] = Integer.parseInt(str[0]);
            ind[i] = Integer.parseInt(str[1]);
            for (int j = 2; j < str.length; j++) {
                int work = Integer.parseInt(str[j]);
                graph[work].add(i);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (ind[i] == 0) {
                q.add(i);
                answer[i] = cost[i];
            }
        }
        while (!q.isEmpty()) {
            int c = q.poll();
            for (int n : graph[c]) {
                ind[n]--;
                // 기존 n까지 의 비용과 이전노드c에서 n으로 가는 새로운 비용으로 비교
                answer[n] = Math.max(answer[c] + cost[n], answer[n]);
                if (ind[n] == 0) {
                    q.add(n);
                }
            }
        }
        int sum = 0;
        for (int i = 1; i < answer.length; i++) {
            sum = Math.max(sum, answer[i]);
        }
        System.out.println(sum);
    }
}
