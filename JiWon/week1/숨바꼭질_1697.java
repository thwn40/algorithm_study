package JiWon.week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질_1697 {public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    System.out.println(BFS(n,k));
}
    public static int BFS(int n, int k){
        int [] visited = new int[100001];		//탐색할 범위 초기화
        int [] move = {-1,1,2};					//이동 범위
        Queue<Integer> q = new LinkedList();
        visited[n] = 1;							//시작 위치 방문 처리
        q.offer(n);								//큐에 시작 위치 저장
        int level=0;							//순회할 레벨(=move 한번 도는 싸이클) 초기화
        while(!q.isEmpty()){
            int len = q.size();					//큐 사이즈 만큼 순회
            for(int i=0; i<len; i++){
                int x = q.poll();				//큐에서 이동 범위 계산할 위치 저장
                for (int j = 0; j < 3; j++) {
                    int nx;
                    if(n==k) return 0;			//엣지 케이스 처리
                    if(move[j]==2){
                        nx = x*move[j];
                    }
                    else{
                        nx = x+move[j];
                    }
                    if (nx == k) {
                        return level+1;
                    }
                    if(nx>0 && nx<=100000 && visited[nx]==0){
                        visited[nx] =1;			//방문 처리
                        q.offer(nx);			//큐에 현재 위치 저장
                    }
                }
            }level++;		//레벨 순회 끝날 때 마다 ++
        }return 0;
    }
}