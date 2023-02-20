package SeongJun.week16;

import java.util.ArrayList;

public class 미로탈출 {
	public int solution(int n, int start, int end, int[][] roads, int[] traps) {
		int answer = 0;
		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		graph.add(new ArrayList<>());
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] road : roads) {
			graph.get(road[0]).add(new Edge(road[1],road[2]));
		}

		System.out.println(graph);
		return answer;
	}

	public static void main(String[] args) {
		미로탈출 miro = new 미로탈출();
		miro.solution(4,1,4,new int[][]{{1,2,1},{3,1,1},{3,2,1},{2,4,1}},new int[]{2,3});
	}
}

class Edge {
	private int v;
	private int weight;

	@Override
	public String toString() {
		return "Edge{" +
			"v=" + v +
			", weight=" + weight +
			'}';
	}

	public Edge(int v, int weight) {
		this.v = v;
		this.weight = weight;
	}

	public void setEdge(int v,int weight) {
		this.v=v;
		this.weight = weight;
	}
}