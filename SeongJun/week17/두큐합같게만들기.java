package SeongJun.week17;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 두큐합같게만들기 {
	public int solution(int[] queue1, int[] queue2) {
		int answer = 0;
		long sum2 = Arrays.stream(queue2).sum();
		long sum1 = Arrays.stream(queue1).sum();
		if ((sum1+sum2)%2==1) {
			return -1;
		}
		Queue<Integer> queueA = new LinkedList<>();
		Arrays.stream(queue1).forEach(queueA::add);
		Queue<Integer> queueB = new LinkedList<>();
		Arrays.stream(queue2).forEach(queueB::add);

		/**
		 * [2, 7, 2,4] 15
		 * [6, 5, 1] 15
		 */

		while(sum1!=sum2){
			if(answer>600000){
				return -1;
			}
			if (sum1>sum2) {
				Integer poll = queueA.poll();
				sum1 -= poll;
				queueB.add(poll);
				sum2 += poll;
			} else{
				Integer poll = queueB.poll();
				sum2 -= poll;
				queueA.add(poll);
				sum1 += poll;
			}
			answer++;
		}

		return answer;
	}
	public static void main(String[] args) {
		두큐합같게만들기 두큐합같게만들기 = new 두큐합같게만들기();
		두큐합같게만들기.solution(new int[]{1, 2, 1, 2},new int[]{1, 10, 1, 2});

	}
}
