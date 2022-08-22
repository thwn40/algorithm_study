package SeongWoo.week8;

import java.util.*;

public class 최소비용구하기_1916 {

    //Node(도시)를 나타내는 클래스
    private static class Node {
        int number;
        //시작점에서 해당 노드로 가는 총 비용
        int totalPayOff = 1000000000;
        //key = 연결된 node 번호, value = 비용
        Map<Integer, Integer> edgeMap = new HashMap<>();

        public Node(int number) {
            this.number = number;
        }
    }

    //bfs로 각 노드를 탐색하면서 totalPayOff의 최소값을 구한다.
    private static void bfs(Node[] nodeArr, int start, int end) {
        Queue<Node> queue = new LinkedList<>();
        //시작점은 이전 노드가 없어서 비교할 수 없기 때문에 totalPayOff를 0으로 설정해준다.
        nodeArr[start].totalPayOff = 0;
        queue.offer(nodeArr[start]);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            Map<Integer, Integer> edgeMap = node.edgeMap;
            for (Integer nextNodeNumber : edgeMap.keySet()) {
                Node nextNode = nodeArr[nextNodeNumber];
                //현재 노드에서 다음 노드로 가는 총 비용
                int totalPayOff = edgeMap.get(nextNodeNumber) + node.totalPayOff;

                //현재 노드에서 다음 노드로 가는 총 비용이 다음 노드에 저장된 totalPayOff보다 크다면 nextNode를 탐색할 필요가 없다.
                if (totalPayOff >= nextNode.totalPayOff) {
                    continue;
                }
                nextNode.totalPayOff = totalPayOff;

                //도착점에서 더 탐색을 할 필요가 없기 때문에 Queue에 넣지 않는다.
                if (nextNode.number == end) {
                    continue;
                }
                queue.offer(nextNode);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeSize = scanner.nextInt();
        int edgeSize = scanner.nextInt();
        Node[] nodeArr = new Node[nodeSize + 1];

        for (int i = 1; i <= nodeSize; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 0; i < edgeSize; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int payOff = scanner.nextInt();
            //입력으로 복수의 from - > to 가 주어질 수 있다. 가장 작은 비용의 경로로 값을 저장한다.
            if (nodeArr[from].edgeMap.get(to) != null) {
                if (nodeArr[from].edgeMap.get(to) <= payOff) {
                    continue;
                }
            }
            nodeArr[from].edgeMap.put(to, payOff);
        }
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        bfs(nodeArr, start, end);

        System.out.println(nodeArr[end].totalPayOff);
    }
}
