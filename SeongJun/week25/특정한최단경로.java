package SeongJun.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로 {
	static int INF = Integer.MAX_VALUE;
	static int V;
	static ArrayList<ArrayList<Edge>> graph;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int v1 = 0;
		int v2 = 0;

		graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (i == E) {
				v1 = from;
				v2 = to;
				break;
			}
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Edge(to, cost));
			graph.get(to).add(new Edge(from, cost));

		}

		int v1start = 0;
		int v2start = 0;
		int[] dijkstra = dijkstra(1);
		v1start+=dijkstra[v1];
		v2start+=dijkstra[v2];
		if(dijkstra[v1]==INF||dijkstra[v2]==INF)
		{
			System.out.println(-1);
			return;
		}
		//v1을 시작점으로 하는 다익스트라
		int[] dijkstra1 = dijkstra(v1);
		v1start+=dijkstra1[v2];
		v2start+=dijkstra1[V];
		if(dijkstra1[v1]==INF||dijkstra1[v2]==INF)
		{
			System.out.println(-1);
			return;
		}
		//v2을 시작점으로 하는 다익스트라
		int[] dijkstra2 = dijkstra(v2);
		v1start+=dijkstra2[V];
		v2start+=dijkstra2[v1];
		if(dijkstra2[V]==INF||dijkstra2[V]==INF)
		{
			System.out.println(-1);
			return;
		}

		System.out.println(Math.min(v1start,v2start));
	}

	private static int[] dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start,0));

		int[] dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		int[] parent = new int[V + 1];
		pq.add(new Edge(start,0));
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();

			for (Edge edge : graph.get(curr.to)) {

				if (dist[edge.to] > dist[curr.to] + edge.cost) {
					dist[edge.to] = dist[curr.to] + edge.cost;
					parent[edge.to] = curr.to;
					pq.offer(new Edge(edge.to, dist[edge.to]));
				}

			}
		}
		return dist;
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


