package SeongJun.week22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 벽부수고이동하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		sc.nextLine();
		int[][] map = new int[n][k];
		int[] dy = {0, 0, -1, 1};
		int[] dx = {-1, 1, 0, 0};
		ArrayList<Position> wall = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();

			char[] chars = s.toCharArray();
			for (int j = 0; j < chars.length; j++) {
				int i1 = Integer.parseInt(String.valueOf(chars[j]));
				map[i][j]= i1;
			}

		}
		int answer = Integer.MAX_VALUE;
		answer = Math.min(answer,bfs(n, k, map, dy, dx));

		if(answer==Integer.MAX_VALUE){
			System.out.println(-1);
		}
		else{
			System.out.println(answer);
		}


	}

	private static int bfs(int n, int k, int[][] map, int[] dy, int[] dx) {
		boolean[][][] visited = new boolean[n][k][2];

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(0,0,false,1));

		while(!queue.isEmpty()) {
			Position current = queue.poll();

			int y = current.y;
			int x = current.x;
			if(y== n -1&&x== k -1){
				return current.depth;
			}


			for (int i = 0; i < 4; i++) {
				int nextY = y+ dy[i];
				int nextX = x+ dx[i];
				if(0<=nextY&&nextX< k &&0<=nextX&&nextY< n){
					if(current.wall&&map[nextY][nextX]==0&&!visited[nextY][nextX][1]){
						visited[nextY][nextX][1]=true;
						queue.offer(new Position(nextY,nextX, true, current.depth+1));
					}
					if(!current.wall&&map[nextY][nextX]==0&&!visited[nextY][nextX][0]){
						visited[nextY][nextX][0]=true;
						queue.offer(new Position(nextY,nextX, false, current.depth+1));
					}
					if(!current.wall&&map[nextY][nextX]==1){
						visited[nextY][nextX][1]=true;
						queue.offer(new Position(nextY,nextX, true, current.depth+1));
					}
				}
			}

		}
		return Integer.MAX_VALUE;
	}

	static class Position{
		@Override
		public String toString() {
			return "Position{" +
				"y=" + y +
				", x=" + x +
				", wall=" + wall +
				", depth=" + depth +
				'}';
		}

		private int y;
		private int x;
		private boolean wall;

		private int depth;

		public Position(int y, int x, boolean wall, int depth) {
			this.y = y;
			this.x = x;
			this.wall =wall;
			this.depth = depth;
		}
	}
}

