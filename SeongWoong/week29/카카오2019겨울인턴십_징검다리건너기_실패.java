import java.util.LinkedList;
import java.util.Queue;

// 실패 - 다시풀어라
public class 카카오2019겨울인턴십_징검다리건너기_실패 {
    public static void main(String[] args) {
        int[] stones = new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        Solution solution = new Solution();
        System.out.println(solution.solution(stones, k));
    }
    static class Solution {
        public int solution(int[] stones, int k) {
            int answer = 0;
            int[] move = new int[stones.length+2];
            move[0] = 1;
            for (int i = 0; i < stones.length; i++) {
                move[i+1] = 1;

                if (stones[i] == 0) {
                    int c = i;
                    while (stones[c]!=0) {
                        if (c==0) break;
                        move[c] += move[c+1];
                        c--;
                    }
                }
            }
            Queue<Integer> q = new LinkedList<>();
            // 출발지 추가
            q.add(stones.length);
            for (int i = 0; i < stones.length; i++) {
                q.add(stones[i]);
            }
            // 도착지 추가
            q.add(stones.length);
            Queue<Integer> q2 = new LinkedList<>();
            boolean can = true;

            while (can) {
                // 현재 돌
                int i = 0;
                while (!q.isEmpty()) {
                    int t = move[i]; // i번째 돌에서 뛰어야 하는 수
                    // 뛰어야하는 수가 넘으면
                    if (t > k) {
                        can = false;
                        break;
                    }
                    if (t > 1) {
                        int cur = q.poll();
                        q2.add(cur - 1);
                    }

                    // 뛰어넘기 과정
                    for (int j = 2; j < t; j++) {
                        q2.add(q.poll());
                        // 그과정에서 q가 비면 도착
                        if (q.isEmpty()){
                            break;
                        }
                    }
                    // 도착하지 않았으면 돌의 숫자 -1해준다
                    if (!q.isEmpty()) {
                        int cur = q.poll();
                        if (cur-1 <= 0 && i-1>=0) {
                            move[i-1]+=move[i];
                        }
                        q2.add(cur - 1);
                    }
                    // 다음 돌의 시작은 뛴만큼 이동
                    i += t;
                }
                // 끝까지 통과
                if (can) {
                    answer++;
                    q.addAll(q2);
                    q2 = new LinkedList<>();;
                }
            }
            return answer;
        }
    }
}

