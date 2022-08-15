package Inhwan.week5;

import java.io.*;
import java.util.*;

public class 이분그래프_1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());    // 테스트케이스 개수
        int V,E;    // vertex, edge 개수
        boolean isBipartite;    // 이분그래프인지 확인

        for (int k = 0; k < K; k++) {
            isBipartite = true;

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 인접 리스트 구현/////
            List<Integer>[] adj = new ArrayList[V+1];
            for (int i = 0; i < V+1; i++) adj[i] = new ArrayList<>();

            int v1, v2;
            for (int i = 0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                v1 = Integer.parseInt(st.nextToken());
                v2 = Integer.parseInt(st.nextToken());

                adj[v1].add(v2);
                adj[v2].add(v1);
            }


            // color를 바꾸기위한 방법으로 map 구현
            Map<String,String> change = new HashMap<>();
            change.put("red","blue");
            change.put("blue","red");

            // 각 점에 칠해진 색을 나타내는 배열
            String[] Colors = new String[V+1];

            // q1 : 현재 단계의 vertexes, q2 : 다음 단계의 vertexes
            Queue<Integer> q1=new LinkedList<>(), q2 = new LinkedList<>();

            // vertex 1 부터 탐색 시작
            for (int i = 1; i <=V ; i++) {
                String color = "blue";

                // 색이 칠해지지 않았으면 (즉 앞의 vertex들과 연결x, 새로운 component)
                // q1에 추가하고 붉은색으로 색칠
                if (Colors[i]==null) {
                    q1.add(i);
                    Colors[i]="red";

                    // q1이 빌 때까지 -> 연결된 모든 vertex들을 탐색
                    while(!q1.isEmpty()) {

                        // q1에서 vertex v를 꺼냄
                        int v = q1.poll();

                        // v와 연결된 vertex 탐색
                        for (int j : adj[v]) {

                            // 색이 칠해지지 않았으면 color 색으로 칠하고 q2에 추가
                            if (Colors[j]==null) {
                                q2.add(j);
                                Colors[j]=color;
                            }

                            // color와 다른 색이 칠해져 있으면 coloring 실패! -> 이분 그래프 x
                            if (Colors[j].equals(change.get(color))) {
                                System.out.println("NO");
                                isBipartite= false;
                                break;
                            }

                        }

                        // for문 탈출
                        if (!isBipartite) break;

                        // q1이 비었을 경우 q1과 q2 교체
                        if (q1.isEmpty()) {
                            color = change.get(color);
                            q1=q2;
                            q2= new LinkedList<>();
                        }
                    }

                    // while문 탈출
                    if (!isBipartite) break;
                }
            }

            // coloring이 성공하면 이분 그래프
            if (isBipartite) System.out.println("YES");
        }
    }
}