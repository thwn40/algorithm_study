package SeongJun.week21;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


public class 알고스팟 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		sc.nextLine();
		int[][] map = new int[k][n];
		int[] dy = {0, 0, -1, 1};
		int[] dx = {-1, 1, 0, 0};
		for (int i = 0; i < k; i++) {
			String s = sc.nextLine();

			char[] chars = s.toCharArray();
			for (int j = 0; j < chars.length; j++) {
				map[i][j]= Integer.parseInt(String.valueOf(chars[j]));
			}

		}
		boolean[][] visited = new boolean[k][n];
		Deque<Position> deque = new LinkedList<>();
		deque.addFirst(new Position(0,0, false,null));
		visited[0][0]=true;
		Position lastPath = null;
		while(!deque.isEmpty()){
			Position current = deque.poll();
			// System.out.println(current);
			if(current.y==k-1&&current.x==n-1){
				lastPath = current;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nextX =current.x+dx[i];
				int nextY =current.y+dy[i];
				if(nextX>=0&&nextX<n&&nextY>=0&&nextY<k){
					if(!visited[nextY][nextX]&&map[nextY][nextX]==0){
						visited[nextY][nextX]=true;
						deque.offerFirst(new Position(nextX,nextY, false, current));
					}
					if(!visited[nextY][nextX]&&map[nextY][nextX]==1){
						visited[nextY][nextX]=true;
						deque.offerLast(new Position(nextX,nextY, true, current));
					}
				}
			}
		}
		int answer = 0;
		while(lastPath.prev!=null){

			if(lastPath.isWall){
				answer++;
			}
			lastPath=lastPath.prev;
		}
		System.out.println(answer);
	}



	static class Position{
		private int x;
		private int y;
		private boolean isWall;
		private Position prev;

		public Position(int x, int y, boolean isWall, Position prev) {
			this.x = x;
			this.y = y;
			this.isWall = isWall;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return "Position{" +
				"x=" + x +
				", y=" + y +
				", isWall=" + isWall +
				", prev=" + prev +
				'}';
		}
	}
}
