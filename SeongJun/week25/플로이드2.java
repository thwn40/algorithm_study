package SeongJun.week25;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 플로이드2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); // number of cities
		int m = scanner.nextInt(); // number of buses

		int[][] dist = new int[n+1][n+1];
		for (int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i],Integer.MAX_VALUE/2);
		}
		for (int i = 0; i <= n; i++) {
			dist[i][i]=0;
		}
		int[][] next = new int[n+1][n+1];
		for (int i = 0; i < n+1; i++) {
			Arrays.fill(next[i],-1);
		}

		for (int i = 0; i < m; i++) {
			int from = scanner.nextInt();
			int to = scanner.nextInt();
			int cost = scanner.nextInt();
			if(dist[from][to]==0){
				next[from][to]=from;
			}else{
				dist[from][to]=Math.min(dist[from][to],cost);
				next[from][to]=to;
			}

		}




		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if(dist[j][k]>dist[j][i]+dist[i][k]){
						dist[j][k]=dist[j][i]+dist[i][k];
						// j에서 k까지 가기위해 j 다음에 방문해야 할 정점
						next[j][k]=next[j][i];
					}
				}
			}
		}

		// for (int i = 1; i <= n; i++) {
		// 	for (int j = 1; j <= n; j++) {
		// 		System.out.print(next[i][j]+" ");
		// 	}
		// 	System.out.println();
		// }
		//
		// System.out.println();

		for (int i = 1; i < dist.length; i++) {
			for (int j = 1; j < dist.length; j++) {
				if(dist[i][j]==Integer.MAX_VALUE/2){
					System.out.print(0+" ");
				}else{
					System.out.print(dist[i][j]+" ");
				}

			}
			System.out.println();
		}
		System.out.println();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {

				if(i==j){
					sb.append("0").append("\n");
				}

				else if(next[i][j]==Integer.MAX_VALUE/2){
					sb.append("0").append("\n");
				}

				else if(next[i][j] == -1){
					sb.append("0").append("\n");
				}
				else{
					Queue<Integer> queue = new LinkedList<>();
					//2 - > 1
					int parent = i; //2
					//2 -> 1 = 4
					//parent 4
					//4 -> 1 = 5
					//parent 5
					//5 -> 1 = 1
					//parent 1
					//1 -> 1 종료
					queue.offer(i);
					while(true){
						if(parent==j){
							break;
						}
						parent = next[parent][j];
						queue.offer(parent);
					}
					sb.append(queue.size()).append(" ");
					while(!queue.isEmpty()){
						sb.append(queue.poll()+" ");
					}
					sb.append("\n");
				}

			}
		}
		System.out.println(sb);

	}


}