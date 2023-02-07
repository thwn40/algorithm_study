package SeongJun.week22;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int currentPosition = sc.nextInt();
		int goalPosition = sc.nextInt();
		if(goalPosition<currentPosition){
			System.out.println(currentPosition-goalPosition);
			System.out.println(1);
			return;
		}
		int answer = Integer.MAX_VALUE;
		Queue<Position2> queue = new LinkedList<>();
		boolean[] visited = new boolean[100000000];
		int count = 0;
		queue.offer(new Position2(currentPosition, 0));
		int min = Integer.MAX_VALUE;
		while (!queue.isEmpty()) {
			Position2 current = queue.poll();

			visited[current.position]=true;
			if (current.position == goalPosition) {
				answer =Math.min(current.time,answer);

				if(current.time==answer){
					count=count+1;
				}

			}

			current.plusTime();

			int position = current.position;

			int back = position-1;
			if (back >= 0 && Boolean.FALSE.equals(visited[back]) ) {
				queue.offer(new Position2(back, current.time));

			}

			int go = position+1;
			if (go <= 100000 && Boolean.FALSE.equals(visited[go]) ) {
				queue.offer(new Position2(go, current.time));

			}


			int teleport = 2 * position;
			if (teleport <= 100000 && Boolean.FALSE.equals(visited[teleport]) ) {
				queue.offer(new Position2(teleport, current.time));

			}

		}

		System.out.println(answer);
		System.out.println(count);


	}
}

class Position2 {
	public int position;
	public int time;

	public Position2(int position, int time) {
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
