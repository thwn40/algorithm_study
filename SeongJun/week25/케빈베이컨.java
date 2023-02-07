package SeongJun.week25;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 케빈베이컨 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine(); // number of cities
		StringTokenizer st = new StringTokenizer(s);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		/**
		 * 그래프가 있는데 n부터 k까지 몇단계를 거쳐서 갈 수 있는가
		 */

		int[][] dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
		}

		int[][] kevin = new int[n][n];
		for (int i = 0; i < m; i++) {
			int from = scanner.nextInt()-1;
			int to = scanner.nextInt()-1;

			dist[from][to] = 1;
			dist[to][from] = 1;
			kevin[from][to] = 1;
		}

		for (int i = 0; i < n; i++) {
			dist[i][i] = 0;
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		int sum = Integer.MAX_VALUE;
		int answer = 0;
		for (int i = 0; i < n; i++) {
			int temp = Arrays.stream(dist[i]).sum();
			if(sum>temp){
				sum=temp;
				answer=i+1;
			}


		}
		System.out.println(answer);

	}
}