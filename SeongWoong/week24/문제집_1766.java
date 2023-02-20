import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 문제집_1766 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        // 연결된 문제
        ArrayList<Integer>[] arr = new ArrayList[N+1];
        for (int i = 1; i <=N ; i++) {
            arr[i] = new ArrayList<>();
        }

        // i번째 문제보다 먼저 풀어야하는 문제개수
        int[] ind = new int[N+1];

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a].add(b);
            ind[b] ++;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (ind[i] == 0) {
                q.offer(i);
            }
        }
        StringBuffer sb = new StringBuffer();
        while(!q.isEmpty()){
            int c = q.poll();
            sb.append(c+" ");
            //c와 연결되어있는 간선의 개수 -1
            for (int n : arr[c] ){
                ind[n]--;
                if (ind[n]==0){
                    q.add(n);
                }
            }
        }
        System.out.println(sb);
    }
}
