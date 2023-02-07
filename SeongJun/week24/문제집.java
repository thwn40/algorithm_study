package SeongJun.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;



public class 문제집 {
	public static void main(String[] args) throws IOException {

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int totalNode = Integer.parseInt(st.nextToken());
		int totalEdge = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= totalNode; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < totalEdge; i++) {
			st = new StringTokenizer(br.readLine());
			graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
		}

		int[] indegrees = new int[totalNode+1];

		for (int i = 0; i <= totalNode; i++) {
			for (int i1 = 0; i1 < graph.get(i).size(); i1++) {
				indegrees[graph.get(i).get(i1)]+=1;
			}
		}

		PriorityQueue<Integer> queue = new PriorityQueue<>();

		IntStream.range(1,totalNode+1).filter(i -> indegrees[i]==0).forEach(queue::add);

		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()){
			Integer cur = queue.poll();
			sb.append(cur).append(" ");

			for (int i = 0; i < graph.get(cur).size(); i++) {
				Integer connected = graph.get(cur).get(i);
				indegrees[connected]-=1;
				if (indegrees[connected]==0) {
					queue.add(connected);
				}
			}
		}
		System.out.println(sb);
	}
}
