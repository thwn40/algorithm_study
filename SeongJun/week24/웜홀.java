package SeongJun.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 웜홀 {
	static int INF = Integer.MAX_VALUE;
	static int V;
	static int E;
	static int W;
	static List<Edge> graph;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		// int TC = 1;
		for (int i = 0; i < TC; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			// V = 3;
			// E = 4;
			// W = 1;
			graph = new ArrayList<>();
			// graph = List.of(
			// new Edge(1,2,3),
			// new Edge(2,1,3),
			// new Edge(2,3,4),
			// new Edge(3,2,4),
			// new Edge(3,1,-8)
			// );


			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph.add(new Edge(from, to, cost));
				graph.add(new Edge(to, from, cost));
			}

			for (int j = 0; j < W; j++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph.add(new Edge(from, to, -cost));
			}

			bellmanFord();

		}
	}

	private static void bellmanFord() {
		int[] distance = new int[V + 1];
		Arrays.fill(distance, 0);

		for (int i = 0; i < V-1; i++) {
			for (Edge edge : graph) {
				//만약 u를 경유하여 v로 가는 거리가 현재 v의 최단 거리보다 짧으면
				if (distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.cost) {
					distance[edge.to] = distance[edge.from] + edge.cost;
				}
			}
		}

		//음수 사이클
		for (Edge edge : graph) {
			if (distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.cost) {
				System.out.println("YES");
				return;
			}
		}

		System.out.println("NO");
	}

	static class Edge {
		private int from;
		private int to;
		private int cost;

		@Override
		public String toString() {
			return "Edge{" +
				"from=" + from +
				", to=" + to +
				", cost=" + cost +
				'}';
		}

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}


