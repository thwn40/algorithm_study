package SeongJun.week22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 열쇠 {
	static Queue<Position> queue = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		for (int t = 0; t < T; t++) {
			int R = sc.nextInt();
			int C = sc.nextInt();
			sc.nextLine();
			char[][] building = new char[R][C];
			boolean[][] visited = new boolean[R][C];
			ArrayList<Position> startingPoint = new ArrayList<>();
			HashMap<Character, Boolean> keyMap = new HashMap<>();
			HashMap<Character, Queue<Position>> waitingDoor = new HashMap<>();
			queue.clear();

			String s = sc.nextLine();
			char[] chars = s.toCharArray();

			//첫줄
			for (int i = 0; i < C; i++) {
				if (chars[i] != '*') {
					startingPoint.add(new Position(0, i));
				}
				building[0][i] = chars[i];
			}

			//중간줄
			for (int i = 1; i < R - 1; i++) {
				s = sc.nextLine();
				chars = s.toCharArray();

				if (chars[0] != '*') {
					startingPoint.add(new Position(i, 0));
				}

				if (chars[C - 1] != '*') {
					startingPoint.add(new Position(i, C - 1));
				}

				for (int j = 0; j < C; j++) {
					building[i][j] = chars[j];
				}

			}

			//막줄
			s = sc.nextLine();
			chars = s.toCharArray();
			for (int j = 0; j < C; j++) {
				if (chars[j] != '*') {
					startingPoint.add(new Position(R - 1, j));
				}
				building[R - 1][j] = chars[j];
			}

			//가지고있던 키
			String keys = sc.nextLine();
			for (char c : keys.toCharArray()) {
				keyMap.put(c, true);
			}

			for (char c = 'a'; c <= 'z'; c++) {
				waitingDoor.put(c, new LinkedList<>());
			}

			for (Position position : startingPoint) {
				char current = building[position.R][position.C];

				if (isKey(current)) {
					keyMap.put(current, true);
					clearWaitingQueue(waitingDoor, current);
					visited[position.R][position.C] = true;
					queue.offer(new Position(position.R, position.C));
				} else if (isDoor(current)) {

					if (hasKey(keyMap, current)) {
						visited[position.R][position.C] = true;
						queue.offer(new Position(position.R, position.C));
					} else {
						waitingDoor.get(Character.toLowerCase(current)).add(new Position(position.R, position.C));
					}

				} else {
					visited[position.R][position.C] = true;
					queue.offer(new Position(position.R, position.C));
				}
			}

			int count = 0;

			while (!queue.isEmpty()) {
				Position current = queue.poll();

				if (building[current.R][current.C] == '$') {
					count++;
				}

				for (int i = 0; i < 4; i++) {
					int nextR = current.R + dr[i];
					int nextC = current.C + dc[i];

					if (!isInRange(R, C, building, visited, nextR, nextC)) {
						continue;
					}

					char next = building[nextR][nextC];
					if (isKey(next)) {
						keyMap.put(next, true);
						clearWaitingQueue(waitingDoor, next);
						visited[nextR][nextC] = true;
						queue.offer(new Position(nextR, nextC));
					} else if (isDoor(next)) {
						if (hasKey(keyMap, next)) {
							visited[nextR][nextC] = true;
							queue.offer(new Position(nextR, nextC));
						} else {
							waitingDoor.get(Character.toLowerCase(next)).add(new Position(nextR, nextC));
						}
					} else {
						visited[nextR][nextC] = true;
						queue.offer(new Position(nextR, nextC));
					}
				}
			}
			System.out.println(count);
		}
	}

	private static boolean hasKey(HashMap<Character, Boolean> keyMap, char next) {
		return Boolean.TRUE.equals(keyMap.getOrDefault(Character.toLowerCase(next), false));
	}

	private static void clearWaitingQueue(HashMap<Character, Queue<Position>> waitingDoor, char next) {
		Queue<Position> positions = waitingDoor.get(Character.toLowerCase(next));

		while (!positions.isEmpty()) {
			queue.offer(positions.poll());
		}
	}

	private static boolean isDoor(char next) {
		return Character.isUpperCase(next);
	}

	private static boolean isKey(char next) {
		return Character.isLowerCase(next);
	}

	private static boolean isInRange(int R, int C, char[][] building, boolean[][] visited, int nextR, int nextC) {
		return nextR >= 0 && nextC >= 0 && nextR < R && nextC < C && !visited[nextR][nextC]
			&& building[nextR][nextC] != '*';
	}

	static class Position {
		private int R;
		private int C;

		public Position(int r, int c) {
			this.R = r;
			this.C = c;
		}

		@Override
		public String toString() {
			return "Position{" + "R=" + R + ", C=" + C + '}';
		}
	}
}


