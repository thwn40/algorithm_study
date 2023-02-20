package SeongWoo.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제집_1766 {

    private static class Node {
        int number;
        //진입차수
        int linkedEdgeCount = 0;
        ArrayList<Node> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    private static ArrayList<Integer> topologicalSort(Node[] nodeArr) {
        ArrayList<Integer> resultList = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.number - n2.number);

        //Queue에 초기값으로 진입차수가 0인값들을 넣어준다
        for (int i = 1; i < nodeArr.length; i++) {
            Node node = nodeArr[i];
            if (node.linkedEdgeCount == 0) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            //Queue에서 꺼내질때 순서가 정해지기 때문에 resultList에 Queue에서 뺀 값을 넣어준다
            resultList.add(currentNode.number);

            for (Node linkedNode : currentNode.edgeList) {
                if (--linkedNode.linkedEdgeCount == 0) {
                    queue.offer(linkedNode);
                }
            }
        }

        return resultList;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int numberSize = Integer.parseInt(st.nextToken());
        int edgeSize = Integer.parseInt(st.nextToken());
        Node[] nodeArr = new Node[numberSize + 1];

        for (int i = 1; i <= numberSize; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 0; i < edgeSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            nodeArr[to].linkedEdgeCount++;
            nodeArr[from].edgeList.add(nodeArr[to]);
        }

        ArrayList<Integer> resultList = topologicalSort(nodeArr);

        for (Integer number : resultList) {
            System.out.print(number +  " ");
        }
    }
}
