package SeongWoo.week29;

import java.util.Deque;
import java.util.LinkedList;

public class 징검다리_건너기 {
    public int solution(int[] stones, int k) {
        Deque<Integer> deque = new LinkedList<>();
        //result = 징검다리를 지나갈 수 있는 캐릭터 수
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < stones.length; i++) {
            //Deque가 비어있는 경우(i = 0 일 때)에는 인덱스를 Deque에 우선적으로 넣어준다.
            if (deque.isEmpty()) {
                deque.offer(i);
            }

            Integer frontIndex = deque.peek();
            //Deque의 가장 앞의 값이 구간을 벗어났다면 Deque에서 제외해준다.
            if (i - frontIndex >= k) {
                deque.poll();
            }

            //Deque의 마지막 값이 새롭게 추가될 값보다 작다면 Deque에서 빼준다.
            while (!deque.isEmpty()) {
                if (stones[deque.peekLast()] <= stones[i]) {
                    deque.pollLast();
                } else {
                    break;
                }
            }
            deque.offer(i);

            //현재 구간의 최대값을 기준으로 result(징검다리를 건널 수 있는 캐릭터 수)를 갱신해준다.
            if (i >= k - 1) {
                result = Math.min(result, stones[deque.peek()]);
            }
        }

        return result;
    }
}
