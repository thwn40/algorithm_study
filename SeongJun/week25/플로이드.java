package SeongJun.week25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 플로이드 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); // number of cities
		int m = scanner.nextInt(); // number of buses

		ArrayList<ArrayList<Edge>> arr = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			arr.add(new ArrayList<>());
		}
		long[][] dist = new long[n][n];
		for (int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i],Integer.MAX_VALUE);
		}
		for (int i = 0; i < m; i++) {
			int from = scanner.nextInt();
			int to = scanner.nextInt();
			int cost = scanner.nextInt();
			arr.get(from).add(new Edge(to,cost));
			dist[from-1][to-1]=Math.min(dist[from-1][to-1],cost);
		}
		for (int i = 0; i < n; i++) {
			dist[i][i]=0;
		}


		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if(dist[j][k]>dist[j][i]+dist[i][k]){
						dist[j][k]=dist[j][i]+dist[i][k];
					}
				}
			}
		}

		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				if(dist[i][j]>=Integer.MAX_VALUE){
					System.out.print(0+" ");
				}else{
					System.out.print(dist[i][j]+" ");
				}

			}
			System.out.println();
		}

	}

	static class Edge{
		private int to;
		private int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}