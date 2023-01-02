import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DSLR_9019_성공 {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "D");
        map.put(1, "S");
        map.put(2, "L");
        map.put(3, "R");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] str = br.readLine().split(" ");
            int A = Integer.parseInt(str[0]);
            int B = Integer.parseInt(str[1]);
            StringBuilder[] route = new StringBuilder[10000];
            boolean[] visited = new boolean[10000];
            Queue<Integer> q = new LinkedList<>();
            q.add(A);
            route[A] = new StringBuilder();
            visited[A] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();

                if (cur == B) break;

                for (int j = 0; j < 4; j++) {
                    int next = run(cur, j);
                    if (!visited[next]){
                        route[next] = new StringBuilder();
                        route[next].append(route[cur]).append(map.get(j));
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
            System.out.println(route[B]);
        }
    }

    public static int run(int C, int dslr) {
        if (dslr == 0) return 2 * C % 10000;
        else if (dslr == 1) return C == 0 ? 9999 : C - 1;
        else if (dslr == 2) return (C % 1000) * 10 + (C / 1000);
        else if (dslr == 3) return (C%10) * 1000 + (C/10);

        return 0;
    }
}
