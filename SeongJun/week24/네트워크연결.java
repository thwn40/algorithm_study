package SeongJun.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크연결 {
	public static void main(String[] args) throws IOException {

		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int totalNode = Integer.parseInt(br.readLine());
		int totalEdge = Integer.parseInt(br.readLine());
		int from;
		int to;
		int cost;

		for (int i = 0; i <= totalNode; i++) {
			graph.add(new ArrayList<>());
		}

		StringTokenizer st = null;
		for (int i = 0; i < totalEdge; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Edge(to,cost));
			graph.get(to).add(new Edge(from,cost));
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		graph.get(1).forEach(pq::offer);
		boolean[] visited = new boolean[totalNode+1];
		visited[1]=true;

		int sum = 0;
		while(!pq.isEmpty()){
			Edge curr = pq.poll();
			if(visited[curr.to]){
				continue;
			}
			visited[curr.to]=true;
			sum+= curr.cost;
			int currEnd = curr.to;
			for (int i = 0; i < graph.get(currEnd).size(); i++) {
				pq.offer(graph.get(currEnd).get(i));
			}
		}
		System.out.println(sum);
	}
	static class Edge implements Comparable<Edge>{
		private int to;
		private int cost;

		@Override
		public String toString() {
			return "Edge{" +
				"end=" + to +
				", cost=" + cost +
				'}';
		}

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost,o.cost);
		}
	}
}


