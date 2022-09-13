package SeongJun.week10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 사회망서비스_2533 {
    static ArrayList<Integer>[] adjList;
    static int[] visited;
    static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        adjList = new ArrayList[N+1];
        dp = new int[N+1][2];
        visited = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            adjList[i]=new ArrayList<>();
        }


        for (int i = 0; i < N-1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adjList[u].add(v);
            adjList[v].add(u);
        }


        dfs(1);
        System.out.println(Math.min(dp[1][0],dp[1][1]));
        System.out.println(Arrays.deepToString(dp));


    }

    static void dfs(int start) {
        ArrayList<Integer> curr = adjList[start];
        visited[start] = 1;
        dp[start][0]=0;
        dp[start][1]=1;
        for (Integer child : curr) {
            if(visited[child]==0){
                dfs(child);
                dp[start][0] += dp[child][1];
                dp[start][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}
