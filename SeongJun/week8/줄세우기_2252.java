package SeongJun.week8;

import java.util.*;

public class 줄세우기_2252 {
    static ArrayList<Integer>[] graph;
    static int[] inDegree;
    static int n;
    static int m;
    static ArrayList<Integer> answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n= sc.nextInt();
        m = sc.nextInt();
        inDegree = new int[n+1];
        graph  = new ArrayList[n+1];
        answer = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            graph[i]= new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int front = sc.nextInt();
            int rear = sc.nextInt();
            graph[front].add(rear);
            inDegree[rear]+=1;
        }

        topologicalSort();
        for (int i = 0; i < answer.size(); i++) {
            if(i==answer.size()-1){
                System.out.println(answer.get(i));
                break;
            }
            System.out.print(answer.get(i) +" ");
        };

    }

    static void topologicalSort(){
        Queue<Integer> q = new LinkedList<>();
        //진입차수가 0인 정점을 큐에 삽입한다.
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i]==0) {
                q.offer(i);
                inDegree[i]=-1;
            }
        }

        // 큐에서 원소를 꺼내 모든 간선을 제거한다.
        while(!q.isEmpty()){
            Integer poll = q.poll();
            answer.add(poll);
            for (int i = 0; i < graph[poll].size(); i++) {
                inDegree[graph[poll].get(i)]-=1;
            }

            //간선 제거 이후에 진입차수가 0이 된 정점을 삽입한다.
            for (int i = 1; i < inDegree.length; i++) {
                if(inDegree[i]==0){
                    q.offer(i);
                    inDegree[i]=-1;
                }
            }

        }

    }



}
