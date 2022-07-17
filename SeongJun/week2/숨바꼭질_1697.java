package SeongJun.week2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Move{
    int distance=0;
    int time=0;

    public Move(int distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Move{" +
                "거리=" + distance +
                ", 시간=" + time +
                '}';
    }
}

public class 숨바꼭질_1697 {


    static int bfs(int n, int k) {
        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(n+1, 0));
        boolean[] visited = new boolean[1000010];

        while (!q.isEmpty()) {
            Move temp = q.poll();


            if (temp.distance == k+1) {
                return temp.time;
            }

            if(temp.distance > 0 && temp.distance <= 100001){
                if(!visited[temp.distance+1]) {
                    q.offer(new Move(temp.distance + 1, temp.time + 1));
                    visited[temp.distance+1]=true;
                }
                if (!visited[temp.distance-1]){
                    q.offer(new Move(temp.distance - 1, temp.time + 1));
                    visited[temp.distance-1]=true;
                }
                if(!visited[temp.distance*2]) {
                    q.offer(new Move(temp.distance * 2, temp.time + 1));
                    visited[temp.distance*2]=true;
                }


            }



        }

        return 1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N,K));


    }
}
