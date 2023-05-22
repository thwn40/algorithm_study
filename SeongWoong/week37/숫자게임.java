package SeongWoong.week37;

import java.util.Arrays;

public class 숫자게임 {
    class Fail {    // 시간초과
        int answer;
        int[] sA, sB;
        boolean[] visit;
        public int solution(int[] A, int[] B) {
            visit = new boolean[B.length];
            sA = A;
            sB = B;
            dfs(0,0);
            return answer;
        }
        public void dfs(int cur, int cnt){
            if (sB.length-cur+cnt<answer) return;
            if (cur==sB.length){
                answer = Math.max(answer, cnt);
                return;
            }

            for(int i = 0; i<sB.length; i++){
                if (visit[i]) continue;
                if (sB[i]>sA[cur]){
                    visit[i] = true;
                    dfs(cur+1, cnt+1);
                    visit[i] = false;
                } else {
                    visit[i] = true;
                    dfs(cur+1, cnt);
                    visit[i] = false;
                }
            }
        }
    }

    class Success {
        public int solution(int[] A, int[] B) {
            int answer = 0;
            Arrays.sort(A);
            Arrays.sort(B);
            int idxA = A.length-1;
            int idxB = B.length-1;
            while(idxA>=0){
                if (A[idxA] < B[idxB]){
                    idxB--;
                    answer++;
                }
                idxA--;
            }
            return answer;
        }
    }
}
