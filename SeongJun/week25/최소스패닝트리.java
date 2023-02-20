package SeongJun.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 최소스패닝트리 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];


		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(from, to, cost));
			//부모를 자기 자신으로 초기화
		}
		for (int i = 1; i <= V; i++) {
			parent[i]=i;
		}

		Collections.sort(edges);
		int sum = 0;
		for (Edge edge : edges) {
			//자신의 최상위 루트노드를 찾는다.
			int from = find(edge.from);
			int to = find(edge.to);
			//같은 사이클이 아니면 MST에 추가
			if(parent[from]!= parent[to]){
				sum+=edge.cost;
				//최소신장트리에 union연산
				union(edge.from, edge.to);
			}
		}

		System.out.println(sum);
	}

	static void union(int from, int to) {
		from = find(from);
		to = find(to);

		if (from == to) {
			return;
		}
		if (from < to) {
			parent[to] = from;
		}
		else{
			parent[from] = to;
		}
	}

	static int find(int x) {
		if(parent[x] == x){
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	static class Edge implements Comparable<Edge> {
		private int from;
		private int to;
		private int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge{" +
				"from=" + from +
				", to=" + to +
				", cost=" + cost +
				'}';
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}


