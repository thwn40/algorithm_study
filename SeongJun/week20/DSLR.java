package SeongJun.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class DSLR {
	static final int MAX = 30000;

	public static void main(String[] args) throws IOException {

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());

		String[] answer = new String[n];

		for (int i = 0; i < n; i++) {

			StringTokenizer input = new StringTokenizer(read.readLine());
			int A = Integer.parseInt(input.nextToken());
			int B = Integer.parseInt(input.nextToken());

			boolean[] visited = new boolean[MAX];
			Queue<Command> queue = new LinkedList<>();
			queue.add(new Command(A, null, null));

			while (!queue.isEmpty()) {
				Command current = queue.poll();
				visited[current.number] = true;
				if (current.number == B) {
					StringBuilder path = getAnswer(current);
					answer[i] = path.toString();
					break;
				}

				Integer number = current.number;
				//Case D
				if (number * 2 >= 9999) {
					if (!visited[(number * 2) % 10000]) {
						visited[(number * 2) % 10000] = true;
						queue.offer(new Command((number * 2) % 10000, 'D', current));

					}
				} else {
					if (!visited[(number * 2)]) {
						visited[number * 2] = true;
						queue.offer(new Command(number * 2, 'D', current));

					}
				}

				//Case S
				if (number == 0) {
					if (!visited[9999]) {
						visited[9999] = true;
						queue.offer(new Command(9999, 'S', current));

					}
				} else {
					if (!visited[number - 1]) {
						visited[number - 1] = true;
						queue.offer(new Command(number - 1, 'S', current));

					}
				}

				//Case L
				int leftRotatedNum = (number % 1000) * 10 + number / 1000;
				if (!visited[leftRotatedNum]) {
					visited[leftRotatedNum] = true;
					queue.offer(new Command(leftRotatedNum, 'L', current));

				}

				//Case R
				int rightRotatedNum = (number / 10) + (number % 10) * 1000;
				if (!visited[rightRotatedNum]) {
					visited[rightRotatedNum] = true;
					queue.offer(new Command(rightRotatedNum, 'R', current));

				}

			}

		}

		for (String s : answer) {
			System.out.println(s);
		}

	}

	private static StringBuilder getAnswer(Command current) {
		Command lastPath = current;

		Stack<Character> stack = new Stack<>();
		while (lastPath.prev != null) {
			stack.push(lastPath.command);
			lastPath = lastPath.prev;
		}

		StringBuilder path = new StringBuilder();

		while (!stack.isEmpty()) {
			path.append(stack.pop());
		}

		return path;
	}

	static class Command {
		private Integer number;
		private Character command;
		private Command prev;

		public Command(Integer number, Character command, Command prev) {
			this.number = number;
			this.command = command;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return "{" +
				", command=" + command +
				", prev=" + prev +
				'}';
		}
	}
}
