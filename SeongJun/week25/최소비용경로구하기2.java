package SeongJun.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용경로구하기2 {
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int start = 0;
		int end = 0;

		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i <= E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (i == E) {
				start = from;
				end = to;
				break;
			}
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Edge(to, cost));

		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start,0));

		int[] dist = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		Edge answer = null;
		int[] parent = new int[V + 1];
		pq.add(new Edge(start,0));
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();

			if (visited[curr.to]) {
				continue;
			}

			visited[curr.to] = true;

			for (Edge edge : graph.get(curr.to)) {

				if (dist[edge.to] > dist[curr.to] + edge.cost) {
					dist[edge.to] = dist[curr.to] + edge.cost;
					parent[edge.to] = curr.to;
					pq.offer(new Edge(edge.to, dist[edge.to]));
				}

			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(dist[end]).

			append("\n");

		ArrayList<Integer> list = new ArrayList<>();
		int next = end;
		list.add(end);
		// System.out.println(Arrays.toString(parent));
		while (parent[next] != 0) {
			list.add(parent[next]);
			next = parent[next];
		}
		Collections.reverse(list);
		sb.append(list.size()).

			append("\n");
		list.forEach(s -> sb.append(s).

			append(" "));

		System.out.println(sb);

	}

	static class Edge implements Comparable<Edge> {
		int to;
		int cost;


		public Edge(int to, int cost) {

			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}

	}
}


