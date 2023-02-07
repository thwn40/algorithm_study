package SeongJun.week20;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int currentPosition = sc.nextInt();
		int goalPosition = sc.nextInt();
		int answer = 0;
		Queue<Position> queue = new LinkedList<>();
		boolean[] visited = new boolean[100000000];
		if(currentPosition==goalPosition){
			System.out.println(answer);
			return;
		}

		queue.offer(new Position(currentPosition, 0));

		while (!queue.isEmpty()) {
			Position current = queue.poll();
			// System.out.println("queue = " + queue);
			// System.out.println("current = " + current);

			if (current.position == goalPosition) {
				answer = current.time;
				break;
			}
			visited[current.position] = true;
			current.plusTime();

			int position = current.position;

			int go = position+1;
			if (go <= 100000 && Boolean.FALSE.equals(visited[go]) ) {
				visited[go] = true;
				queue.offer(new Position(go, current.time));

			}

			int back = position-1;
			if (back >= 0 && Boolean.FALSE.equals(visited[back]) ) {
				visited[back] = true;
				queue.offer(new Position(back, current.time));

			}

			int teleport = 2 * position;
			if (teleport <= 100000 && Boolean.FALSE.equals(visited[teleport]) ) {
				visited[teleport] = true;
				queue.offer(new Position(teleport, current.time));

			}

		}

		System.out.println(answer);

	}
}

class Position {
	public int position;
	public int time;

	public Position(int position, int time) {
		this.position = position;
		this.time = time;
	}

	void plusTime() {
		time = time + 1;
	}

	@Override
	public String toString() {
		return "{" +
			"position=" + position +
			", time=" + time +
			'}';
	}
}
