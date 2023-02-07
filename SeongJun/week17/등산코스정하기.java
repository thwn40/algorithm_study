package SeongJun.week17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 등산코스정하기 {
	class Edge implements Comparable<Edge> {
		private Integer to;
		private Integer intensity;

		public Edge(Integer to, Integer intensity) {
			this.to = to;
			this.intensity = intensity;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.intensity, o.intensity);
		}

		@Override
		public String toString() {
			return "Edge{" +
				"num=" + to +
				", intensity=" + intensity +
				'}';
		}
	}

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		Map<Integer, Boolean> gatesMap = new HashMap();
		Map<Integer, Boolean> summitMap = new HashMap();
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[n+1];
		int[] intensities = new int[n + 1];

		//그래프 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
			intensities[i] = Integer.MAX_VALUE;
		}

		//출입구
		for (int gate : gates) {
			gatesMap.put(gate, true);
			queue.add(new Edge(gate, 0));
			intensities[gate] = 0;
		}

		//정상
		for (int summit : summits) {
			summitMap.put(summit, true);
		}

		//그래프 삽입
		for (int[] path : paths) {
			int from = path[0];
			int to = path[1];
			int intensity = path[2];

			graph.get(to).add(new Edge(from, intensity));
			graph.get(from).add(new Edge(to, intensity));
		}


		while (!queue.isEmpty()) {
			Edge now = queue.poll();

			if(!visited[now.to]){
				visited[now.to]= true;
			}

			if (summitMap.containsKey(now.to)) {
				continue;
			}

			if (intensities[now.to] < now.intensity) {
				continue;
			}

			for (Edge next : graph.get(now.to)) {
				int max = Math.max(now.intensity, next.intensity);

				if (gatesMap.containsKey(next.to)) {
					continue;
				}

				// 다익스트라는 가중치의 합의 최소가 되는경우
				// 얘는 가중치의 최대값이 최소가 되는 경우
				if (!visited[next.to]&&intensities[next.to] > max) {
					intensities[next.to] = max;
					queue.add(new Edge(next.to, intensities[next.to]));
				}

			}
		}

		int index = -1;
		int minIntensity = Integer.MAX_VALUE;
		Arrays.sort(summits);

		for (int summit : summits) {

			if (intensities[summit] < minIntensity) {
				minIntensity = intensities[summit];
				index = summit;
			}

		}

		return new int[] {index, minIntensity};
	}

	public static void main(String[] args) {
		등산코스정하기 T = new 등산코스정하기();

		int n = 7;
		int[][] paths = {{4, 1, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
		int[] gates = {1};
		int[] summits = {2, 3, 4};
		System.out.println(Arrays.toString(T.solution(n, paths, gates, summits)));
	}
}
