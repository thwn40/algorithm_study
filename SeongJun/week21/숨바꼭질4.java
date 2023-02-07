package SeongJun.week21;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class 숨바꼭질4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int currentPosition = sc.nextInt();
		int goalPosition = sc.nextInt();
		int answer = 0;
		Position4 lastPath = null;
		Queue<Position4> queue = new LinkedList<>();
		boolean[] visited = new boolean[100000000];
		if(currentPosition==goalPosition){
			System.out.println(answer);
			System.out.println(currentPosition);
			return;
		}

		queue.offer(new Position4(currentPosition, 0,null));

		while (!queue.isEmpty()) {
			Position4 current = queue.poll();


			if (current.position == goalPosition) {
				answer = current.time;
				lastPath = current;
				break;
			}
			visited[current.position] = true;
			current.plusTime();

			int position = current.position;

			int go = position+1;
			if (go <= 100000 && Boolean.FALSE.equals(visited[go]) ) {
				visited[go] = true;
				queue.offer(new Position4(go, current.time,current));

			}

			int back = position-1;
			if (back >= 0 && Boolean.FALSE.equals(visited[back]) ) {
				visited[back] = true;
				queue.offer(new Position4(back, current.time,current));

			}

			int teleport = 2 * position;
			if (teleport <= 100000 && Boolean.FALSE.equals(visited[teleport]) ) {
				visited[teleport] = true;
				queue.offer(new Position4(teleport, current.time,current));

			}

		}

		System.out.println(answer);
		Stack<Integer> path = new Stack<>();
		while(lastPath.prevNode!=null){
			path.push(lastPath.position);
			lastPath=lastPath.prevNode;
		}
		path.push(lastPath.position);


		StringBuilder path2 = new StringBuilder();
		while(!path.isEmpty()){
			path2.append(path.pop()).append(" ");
		}

		System.out.println(path2);
	}
}

class Position4 {
	public int position;
	public int time;
	public Position4 prevNode;

	public Position4(int position, int time, Position4 prevNode) {
		this.position = position;
		this.time = time;
		this.prevNode = prevNode;
	}

	void plusTime() {
		time = time + 1;
	}

	@Override
	public String toString() {
		return "Position4{" +
			"position=" + position +
			", time=" + time +
			", prevNode=" + prevNode +
			'}';
	}
}
