import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 줄세우기_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int[] edgeCount = new int[N+1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N+1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            graph.get(start).add(end);
            edgeCount[end]++;
        }
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N ; i++) {
            if (edgeCount[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.println(cur);

            // cur와 연결된 정보
            List<Integer> list = graph.get(cur);

            for (int i = 0; i < list.size(); i++) {
                int temp = list.get(i);
                edgeCount[temp]--;
                if (edgeCount[temp] == 0){
                    q.add(temp);
                }
            }
        }
    }
}
