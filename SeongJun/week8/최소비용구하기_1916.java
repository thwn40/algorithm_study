package SeongJun.week8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;



    public class 최소비용구하기_1916 {
        static final int INF = Integer.MAX_VALUE;
        static int[] distTable;
        static ArrayList<Edge>[] Graph;
        static int u, v, dist, V, E, K;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            V = sc.nextInt();
            //출발점으로부터의 최단거리를 저장할 배열 distTable
            distTable = new int[V];
            Graph = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                Graph[i] = new ArrayList<>();
            }

            E = sc.nextInt();

            for (int i = 0; i < E; i++) {
                u = sc.nextInt() - 1;
                v = sc.nextInt() - 1;
                dist = sc.nextInt();
                Graph[u].add(new Edge(v, dist));
            }
            K = sc.nextInt() - 1;
            int destination = sc.nextInt()-1;
            dijkstra();

            for (int i = 0; i < distTable.length; i++) {
                if(i==destination){
                    System.out.println(distTable[i]);
                }
            }

        }

        static void dijkstra() {
            //출발노드에는 0을, 출발점을 제외한 다른 노드들에는 `INF`로 초기화
            Arrays.fill(distTable, INF);
            distTable[K] = 0;

            PriorityQueue<Edge> queue = new PriorityQueue<>();
            queue.offer(new Edge(K, 0));

            while (!queue.isEmpty()) {

                //우선순위큐에서 거리가 가장 작은 노드를 선택
                Edge currEdge = queue.poll();
                int currVertex = currEdge.destVertex;
                int currDist = currEdge.dist;

                //해당 거리가 최단거리 테이블에 있는 값과 다를 경우 넘어감
                if (distTable[currVertex] !=currDist) {
                    continue;
                }

                for (int v = 0; v < Graph[currVertex].size(); v++) {

                    Edge edge = Graph[currVertex].get(v);
                    //`currVertex`와 이웃한 정점들에 대해 최단 거리 테이블 값보다 `currVertex`를 거처가는 것이 더 작은 값을 가질 경우
                    if (distTable[edge.destVertex] <= edge.dist + currDist) continue;
                    //최단 거리 테이블의 값을 갱신
                    distTable[edge.destVertex] = edge.dist + currDist;
                    //우선순위 큐에 (이웃한 정점의 번호, 거리)를 추가
                    queue.add(new Edge(edge.destVertex, edge.dist + currDist));
                }

            }


        }

        static class Edge implements Comparable<Edge> {
            int destVertex;
            int dist;

            @Override
            public String toString() {
                return "{" +
                        + dist +
                        ", " + (destVertex + 1) +
                        '}';
            }

            public Edge(int destVertex, int dist) {
                this.destVertex = destVertex;
                this.dist = dist;
            }

            @Override
            public int compareTo(Edge o) {
                return Integer.compare(this.dist, o.dist);
            }
        }
    }


