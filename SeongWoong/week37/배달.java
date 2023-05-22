package SeongWoong.week37;
import java.util.*;
public class 배달 {
    class Success {
        public int solution(int N, int[][] road, int K) {
            int answer = 0;
            boolean[][] visit = new boolean[N+1][N+1];  // visit[i][j] = i에서 j로가는 중복체크 배열
            int[][] times = new int[N+1][N+1];  // times[i][j] = i에서 j로 가는데 걸리는 시간
            List<Integer>[] list = new ArrayList[N+1];  // list.get(i) = i마을에 연결되어있는 마을 리스트
            for(int i=0;i<=N;i++){
                list[i] = new ArrayList<>();
                Arrays.fill(times[i], 25000000);    // 정답배열을 처음엔 큰값으로 입력
            }
            for(int i =0;i<road.length;i++){
                int from = road[i][0];
                int to = road[i][1];
                int time = road[i][2];
                list[from].add(to);     // 양방향 그래프이므로 양방향을 다 넣는다.
                list[to].add(from);
                times[from][to] = Math.min(times[from][to], time);
                times[to][from] = Math.min(times[to][from], time);
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            times[0][1] = 0;
            while(!q.isEmpty()){
                int cur = q.poll();
                for(int i=0; i<list[cur].size(); i++){
                    int next = list[cur].get(i);
                    if(visit[cur][next]) continue;
                    visit[cur][next] = true;
                    q.add(next);
                    int newTime = times[0][cur] + times[cur][next];
                    if (newTime <times[0][next]){   // 새롭게 갱신된 경우 다시 해당 마을에 연결된 시간을 다시 구해야하므로 visit배열 초기화
                        times[0][next] = newTime;
                        Arrays.fill(visit[next],false);
                    }
                }
            }
            for(int t : times[0]){  // 정답배열을 돌면서 K보다 작으면 카운트
                System.out.println(t);
                if(t<=K) answer++;
            }
            return answer;
        }
    }
    class Fail{
        public int solution(int N, int[][] road, int K) {
            int answer = 0;
            boolean[][] visit = new boolean[N+1][N+1];
            int[][] times = new int[N+1][N+1];
            List<Integer>[] list = new ArrayList[N+1];
            for(int i=0;i<=N;i++){
                list[i] = new ArrayList<>();
                Arrays.fill(times[i], 500000);
            }
            for(int i =0;i<road.length;i++){
                int from = road[i][0];
                int to = road[i][1];
                int time = road[i][2];
                list[from].add(to);
                list[to].add(from);
                times[from][to] = Math.min(times[from][to], time);
                times[to][from] = Math.min(times[to][from], time);
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            times[0][1] = 0;
            while(!q.isEmpty()){
                int cur = q.poll();
                for(int i=0; i<list[cur].size(); i++){
                    int next = list[cur].get(i);
                    if(visit[cur][next]) continue;
                    visit[cur][next] = true;
                    q.add(next);
                    times[0][next] = Math.min(times[0][next], times[0][cur] + times[cur][next]);
                }
            }
            for(int t : times[0]){
                System.out.println(t);
                if(t<=K) answer++;
            }
            return answer;
        }
    }
}
