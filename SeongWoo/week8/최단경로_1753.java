package SeongWoo.week8;

import java.util.*;

public class 최단경로_1753 {

    //간선을 나타내는 클래스
    private static class Edge {
        //간선이 향하는 노드
        int to;
        //해당 간선의 가중치
        int payOff;

        public Edge(int to, int payOff) {
            this.to = to;
            this.payOff = payOff;
        }
    }

    //노드(정점)을 나타내는 클래스
    private static class Node {
        int number;
        //시작점 부터 해당 노드까지 이동하는데 필요한 비용
        int totalPayOff = 10000000;
        boolean check = false;
        ArrayList<Edge> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    //다익스트라를 사용해서 start로 부터 각 노드까지의 최소 비용을 구한다.
    private static void bfs(Node[] nodeArr, int start) {
        /*
        Queue에는 Edge가 들어간다.
        이 Queue에 들어가는 Edge는 Node의 Edge와는 다르다.
        payOff가 to노드로 이어지는 간선의 가중치가 아니라 start로 부터 to까지의 이동하는데 필요한 비용을 의미한다.
        해당 Queue는 start로 부터 to까지 이동하는데 필요한 비용을 기준으로 오름차순 정렬된다.
        */
        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> e1.payOff < e2.payOff ? -1 : 1);
        nodeArr[start].totalPayOff = 0;
        queue.offer(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge queueEdge = queue.poll();
            Node node = nodeArr[queueEdge.to];

            //아래 반복문에서는 간선을 Queue에 넣는것이기 때문에 node에 대한 check를 여기서 한다.
            if (node.check) {
                continue;
            }
            node.check = true;
            //Queue에서 뽑은 Edge의 to는 start에서 to로 가는 총 비용이 가장 적다.
            //to와 연결된 간선을 전부 탐색하며 이어진 노드의 totalPayOff를 갱신한다.
            //탐색한 간선은 전부 Queue에 넣어준다. 간선의 노드가 check됐다면 나중에 반복문에서 알아서 걸러진다.
            for (int i = 0; i < node.edgeList.size(); i++){
                Edge edge = node.edgeList.get(i);
                Node nextNode = nodeArr[edge.to];
                int totalPayOff = node.totalPayOff + edge.payOff;
                //간선과 연결된 노드의 totalPayOff를 갱신한다.
                if (nextNode.totalPayOff <= totalPayOff) {
                    continue;
                }
                nextNode.totalPayOff = totalPayOff;
                queue.offer(new Edge(nextNode.number, nextNode.totalPayOff));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeSize = scanner.nextInt();
        int edgeSize = scanner.nextInt();
        int start = scanner.nextInt();
        Node[] nodeArr = new Node[nodeSize + 1];

        for (int i = 1; i <= nodeSize; i++) {
            nodeArr[i] = new Node(i);
        }
        for (int i = 0; i < edgeSize; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int payOff = scanner.nextInt();

            nodeArr[from].edgeList.add(new Edge(to, payOff));
        }

        bfs(nodeArr, start);

        for (int i = 1; i < nodeArr.length; i++) {
            if (nodeArr[i].totalPayOff == 10000000) {
                System.out.println("INF");
            } else {
                System.out.println(nodeArr[i].totalPayOff);
            }
        }
    }
}
