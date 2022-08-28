package Inhwan.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 줄세우기_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        List<Integer>[] Greater = new List[N+1];
        for (int i = 0; i < N+1; i++) Greater[i] = new ArrayList<>();

        int[] underCount = new int[N+1];

        int less, greater;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            less = Integer.parseInt(st.nextToken());
            greater = Integer.parseInt(st.nextToken());

            Greater[less].add(greater);
            underCount[greater]++;
        }

        int[] Ordered = new int[N];

        boolean[] check = new boolean[N+1];
        check[0]=true;

        Queue<Integer> minimal = new LinkedList();
        int index = 0;

        while(index < N) {
            for (int i = 1; i < N+1 ; i++) {
                if (!check[i] && underCount[i]==0) {
                    check[i]=true;
                    minimal.add(i);
                }
            }

            while (!minimal.isEmpty()) {
                int m = minimal.poll();

                for (int g : Greater[m]) {
                    underCount[g]--;
                }

                Ordered[index++] = m;
            }
        }

        System.out.print(Ordered[0]);
        for (int i = 1; i < N; i++) System.out.print(" "+Ordered[i]);
    }
}