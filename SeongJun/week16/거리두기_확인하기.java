package SeongJun.week16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 거리두기_확인하기 {
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, 1, 0, -1};

	public int[] solution(String[][] places) {
		List<Boolean> answerList = new ArrayList<>();

		for (int i = 0; i < places.length; i++) {
			char[][] place = toCharArr(places[i]);
			boolean answer;
			answer = isAnswer(i, place);
			answerList.add(answer);
		}
		return answerList.stream().mapToInt(this::booleanToInt).toArray();
	}

	private boolean isAnswer(int i, char[][] place) {
		for (int j = 0; j < place.length; j++) {

			for (int k = 0; k < place[i].length; k++) {

				if (place[j][k] != 'P') {
					continue;
				}
				if(!bfs(place, j, k)){
					return false;
				}

			}

		}
		return true;
	}

	private int booleanToInt(Boolean a) {
		if (Boolean.TRUE.equals(a)) {
			return 1;
		}

		return 0;
	}

	private boolean bfs(char[][] charPlaces, int j, int k) {
		boolean[][] visited = new boolean[5][5];
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(j, k, 0));
		visited[j][k] = true;

		while (!queue.isEmpty()) {
			Position poll = queue.poll();

			if (poll.depth > 2) {
				continue;
			}

			if (poll.depth != 0 && charPlaces[poll.x][poll.y] == 'P') {
				return false;
			}

			for (int l = 0; l < 4; l++) {
				int nx = poll.x + dx[l];
				int ny = poll.y + dy[l];

				if (checkRange(visited, nx, ny)) {

					if (charPlaces[nx][ny] == 'X') {
						continue;
					}

					visited[nx][ny] = true;
					queue.offer(new Position(nx, ny, poll.depth + 1));
				}

			}

		}
		return true;
	}

	private boolean checkRange(boolean[][] visited, int nx, int ny) {
		return nx >= 0 && nx <= 4 && ny >= 0 && ny <= 4 && !visited[nx][ny];
	}

	private char[][] toCharArr(String[] places) {
		char[][] charPlaces = new char[5][5];
		//구하기쉽게 char 배열로
		for (int m = 0; m < places.length; m++) {
			charPlaces[m] = places[m].toCharArray();
		}
		return charPlaces;
	}

	class Position {
		int x;
		int y;
		int depth;

		public Position(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Position{" + "x=" + x + ", y=" + y + ", depth=" + depth + '}';
		}
	}


	public static void main(String[] args) {
		거리두기_확인하기 asdf = new 거리두기_확인하기();
		asdf.solution(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
			{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
			{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});
	}

}
