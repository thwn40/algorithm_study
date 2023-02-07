package SeongJun.week22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 탈출 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		char[][] map = new char[R][C];
		int[] dy = {0, 0, -1, 1};
		int[] dx = {-1, 1, 0, 0};
		int[][] flooding = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		sc.nextLine();
		List<Position> waterStartingPoint = new ArrayList<>();
		Position gasiStartPoint = null;
		for (int i = 0; i < R; i++) {
			String s = sc.nextLine();
			char[] chars = s.toCharArray();
			for (int j = 0; j < C; j++) {
				if (chars[j] == '*') {
					waterStartingPoint.add(new Position(i, j, true, false,0));
				}
				if (chars[j] == 'S') {
					gasiStartPoint = new Position(i, j, false, true,0);
				}
				map[i][j] = chars[j];
			}
		}

		Queue<Position> queue = new LinkedList<>();
		queue.offer(gasiStartPoint);
		waterStartingPoint.forEach(queue::offer);

		while (!queue.isEmpty()) {
			Position current = queue.poll();


			if (current.isGaSi) {
				if (map[current.r][current.c] == 'D') {
					System.out.println(current.depth);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nextR = current.r + dy[i];
					int nextC = current.c + dx[i];
					if(!(nextR>=0&&nextR<R&&nextC>=0&&nextC<C)){
						continue;
					}
					if (map[nextR][nextC] != 'X' && !visited[nextR][nextC] && flooding[current.r][current.c]==0) {
						visited[nextR][nextC] = true;
						queue.offer(new Position(nextR, nextC, false, true, current.depth + 1));
					}
				}
			}
			if (current.isWater) {
				for (int i = 0; i < 4; i++) {
					int nextR = current.r + dy[i];
					int nextC = current.c + dx[i];
					if(!(nextR>=0&&nextR<R&&nextC>=0&&nextC<C)){
						continue;
					}
					if (map[nextR][nextC] != 'X' && map[nextR][nextC] != 'D' && flooding[nextR][nextC]==0) {
						flooding[nextR][nextC] = flooding[current.r][current.c]+1;
						queue.offer(new Position(nextR, nextC, true, false, current.depth + 1));
					}
				}
			}
		}

		System.out.println("KAKTUS");


	}

	static class Position {
		@Override
		public String toString() {
			return "Position{" +
				"r=" + r +
				", c=" + c +
				", isWater=" + isWater +
				", isGaSi=" + isGaSi +
				", depth=" + depth +
				'}';
		}

		private int r;
		private int c;
		private boolean isWater;
		private boolean isGaSi;
		private int depth;

		public Position(int r, int c, boolean isWater, boolean isGaSi, int depth) {
			this.r = r;
			this.c = c;
			this.isWater = isWater;
			this.isGaSi = isGaSi;
			this.depth = depth;
		}
	}
}
