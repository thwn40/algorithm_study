import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 이분그래프_1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // K는 테스트케이스의 수
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            String[] str = br.readLine().split(" ");
            //V는 정점의 수
            int V = Integer.parseInt(str[0]);
            //E는 간선의 수
            int E = Integer.parseInt(str[1]);
            // 정점을 1과 2로 구분한다 구분안된 정점은 0
            int[] arr = new int[V + 1];
            ArrayList<Integer>[] list = new ArrayList[V + 1];
            for (int j = 0; j <= V; j++) {
                list[j] = new ArrayList<Integer>();
            }
            for (int j = 0; j < E; j++) {
                str = br.readLine().split(" ");
                // 연결된 간선정보
                int u = Integer.parseInt(str[0]);
                int v = Integer.parseInt(str[1]);
                list[u].add(v);
                list[v].add(u);
            }
            Queue<Integer> q = new LinkedList<>();
            boolean isBip = false;
            for (int j = 1; j <= V; j++) {
                if (arr[j] != 0) continue;
                arr[j] = 1;
                q.add(j);
                while (!q.isEmpty()) {
                    isBip = true;
                    int c = q.poll();
                    for (int k = 0; k < list[c].size(); k++) {
                        int next = list[c].get(k);
                        if (arr[next] != 0) {
                            if (arr[c] == arr[next]) {
                                isBip = false;
                                break;
                            }
                        }else if(arr[next]==0){
                            if (arr[c] == 1) arr[next] = 2;
                            else if (arr[c] == 2) arr[next] = 1;
                            q.add(next);
                        }
                    }
                    if (!isBip) break;
                }
                if (!isBip) break;
            }
            if (isBip) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}