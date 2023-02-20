package SeongJun.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 타임머신 {
	static int INF = Integer.MAX_VALUE;
	static int V;
	static int E;
	static ArrayList<Edge> graph;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();


		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.add(new Edge(from, to, cost));
		}

		bellmanFord();
	}

	private static void bellmanFord() {
		int[] distance = new int[V + 1];
		Arrays.fill(distance, INF);
		distance[1] = 0;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < E; j++) {
				Edge edge = graph.get(j);
				//만약 u를 경유하여 v로 가는 거리가 현재 v의 최단 거리보다 짧으면
				if (distance[edge.from]!= INF && distance[edge.to] > distance[edge.from]+edge.cost){
					distance[edge.to] = distance[edge.from] + edge.cost;
				}
			}
		}

		//음수 사이클
		for (int i = 0; i < E; i++) {
			Edge edge = graph.get(i);

			if(distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.cost){
				System.out.println("-1");
				return;
			}
		}

		for (int i = 2; i < distance.length; i++) {
			if(distance[i]== INF){
				System.out.println("-1");
			}
			else{
				System.out.println(distance[i]);
			}
		}
	}

	static class Edge {
		private int from;
		private int to;
		private int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}


