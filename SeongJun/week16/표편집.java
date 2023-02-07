package SeongJun.week16;

import java.util.Stack;

public class 표편집 {
	public String solution(int n, int k, String[] cmd) {
		StringBuilder answer = new StringBuilder();
		Stack<Integer> removed = new Stack<>();
		int arrSize = n;
		int cursor = k;

		for (String s : cmd) {
			String[] commands = s.split(" ");
			if(commands[0].equals("U")){
				cursor-=Integer.parseInt(commands[1]);
				continue;
			}

			if(commands[0].equals("D")){
				cursor+=Integer.parseInt(commands[1]);
				continue;

			}

			if(commands[0].equals("C")){

				removed.add(cursor);
				arrSize--;
				if(cursor>arrSize-1){
					cursor--;
				}
				continue;
			}

			if(commands[0].equals("Z")){
				Integer pop = removed.pop();
				if(pop<=cursor) cursor++;
				arrSize++;
			}
		}

		for (int i = 0; i < arrSize; i++) {
			answer.append("O");
		}

		while(!removed.isEmpty()) {
			int z = removed.pop();
			answer.insert(z,'X');
		}

		return answer.toString();
	}
	public static void main(String[] args) {
		표편집 solution = new 표편집();
		System.out.println(	solution.solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));

	}
}
