import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬하기_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }

        int result = 0;

        while(pq.size() != 1) {
            long sum = pq.poll() + pq.poll();
            result += sum;
            pq.add(sum);
        }
        System.out.println(result);
    }
}
