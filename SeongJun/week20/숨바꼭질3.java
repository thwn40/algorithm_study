package SeongJun.week20;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int currentPosition = sc.nextInt();
		// int currentPosition = 5;
		int goalPosition = sc.nextInt();
		// int goalPosition = 17;
		int answer = Integer.MAX_VALUE;
		Queue<Position3> queue = new LinkedList<>();
		boolean[] visited = new boolean[100001];

		// if(currentPosition==goalPosition){
		// 	System.out.println(answer);
		// 	return;
		// }

		queue.offer(new Position3(currentPosition, 0,0));

		while (!queue.isEmpty()) {
			Position3 current = queue.poll();
			// System.out.println("queue = " + queue);
			// System.out.println("current = " + current);

			if (current.position == goalPosition) {
				answer = Math.min(answer,current.time);

			}
			visited[current.position] = true;


			int position = current.position;

			current.plusTime();
			int go = position+1;
			if (go <= 100000 && !visited[go]) {


				queue.offer(new Position3(go, 1,current.time));

			}

			int back = position-1;
			if (back >= 0 && !visited[back]) {

				queue.offer(new Position3(back,1, current.time));

			}

			int teleport = 2 * position;
			current.minusTime();
			if (teleport <= 100000 && !visited[teleport]) {

				queue.offer(new Position3(teleport, 0,current.time));

			}



		}


		System.out.println(answer);

	}
}

class Position3 {
	public int position;
	public int weight;
	public int time;

	public Position3(int position, int weight, int time) {
		this.position = position;
		this.weight = weight;
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

	public void minusTime() {
		time = time - 1;
	}

}
