package SeongJun.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 작업 {
	public static void main(String[] args) throws IOException {

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int totalNode = Integer.parseInt(br.readLine());

		for (int i = 0; i <= totalNode; i++) {
			graph.add(new ArrayList<>());
		}

		StringTokenizer st = null;
		int[] gajoongchi = new int[totalNode+1];
		int[] indegrees = new int[totalNode+1];
		int[] dp = new int[totalNode+1];
		dp[1]=indegrees[1];
		for (int i = 1; i <= totalNode; i++) {
			String s = br.readLine();
			st= new StringTokenizer(s);
			gajoongchi[i]=Integer.parseInt(st.nextToken());
			int indegree = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= indegree; j++) {
				graph.get(Integer.parseInt(st.nextToken())).add(i);
				indegrees[i]+=1;
			}

		}


		Queue<Integer> queue = new LinkedList<>();

		IntStream.range(1,totalNode+1).filter(i -> indegrees[i]==0).forEach(e -> {
			dp[e]=gajoongchi[e];
			queue.add(e);
		});

		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> ans = new ArrayList<>();
		while(!queue.isEmpty()){
			Integer cur = queue.poll();
			ans.add(cur);

			for (int i = 0; i < graph.get(cur).size(); i++) {
				Integer connected = graph.get(cur).get(i);
				indegrees[connected]-=1;
				dp[connected] = Math.max(dp[connected], dp[cur] + gajoongchi[connected]);
				if (indegrees[connected]==0) {
					queue.add(connected);
				}
			}
		}

		System.out.println(Arrays.stream(dp).max().getAsInt());

	}
}
