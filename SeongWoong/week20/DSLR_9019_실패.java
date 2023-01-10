import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DSLR_9019_실패 {
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
            long[] route = new long[10000];
            Queue<Integer> q = new LinkedList<>();
            q.add(A);
            route[A] = 5;
            while (!q.isEmpty()) {
                int cur = q.poll();

                if (cur == B) break;

                for (int j = 0; j < 4; j++) {
                    int next = run(cur, j);
                    if (route[next]==0){
                        //todo: 틀린원인 : 여기서 경로에 따라서 route[Number] 가 길어지는데 int나 long길이를 넘어버리는 상황 발생
                        route[next] = route[cur]*10 + j;
                        q.add(next);
                    }
                    System.out.println(route[next]);
                }
            }
            String answer = Long.toString(route[B]).replace("0", "D").replace("1", "S").replace("2", "L").replace("3", "R").replace("5","");
            System.out.println(answer);
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
