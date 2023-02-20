package SeongWoo.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 작업_2056 {

    private static class Node {
        int number;
        int linkedEdgeCount = 0;
        int workTime;
        //작업에 따른 누적 시간(최대값)
        int accumulateTime;
        ArrayList<Node> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    private static int topologicalSort(Node[] nodeArr) {
        //총 작업시간이 걸릴 변수
        int maxTime = 0;
        Queue<Node> queue = new LinkedList<>();

        //Queue에 초기값(진입차수 0)을 넣어줌
        for (int i = 1; i < nodeArr.length; i++) {
            Node node = nodeArr[i];
            if (node.linkedEdgeCount == 0) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (maxTime < currentNode.accumulateTime) {
                maxTime = currentNode.accumulateTime;
            }

            for (Node linkedNode : currentNode.edgeList) {
                //선행작업(currentNode) 후의 작업(linkedNode)에 걸리는 시간의 최대값을 구한다
                int tempAccumulateTime = linkedNode.workTime + currentNode.accumulateTime;
                if (linkedNode.accumulateTime < tempAccumulateTime) {
                    linkedNode.accumulateTime = tempAccumulateTime;
                }

                if (--linkedNode.linkedEdgeCount == 0) {
                    queue.offer(linkedNode);
                }
            }
        }

        return maxTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        Node[] nodeArr = new Node[nodeSize + 1];

        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 1; i < nodeArr.length; i++) {
            Node to = nodeArr[i];
            st = new StringTokenizer(br.readLine(), " ");

            to.workTime = Integer.parseInt(st.nextToken());
            to.accumulateTime = to.workTime;

            int precedingWorkSize = Integer.parseInt(st.nextToken());
            to.linkedEdgeCount = precedingWorkSize;
            for (int j = 0; j < precedingWorkSize; j++) {
                int number = Integer.parseInt(st.nextToken());
                Node from = nodeArr[number];
                from.edgeList.add(to);
            }
        }

        int result = topologicalSort(nodeArr);

        System.out.println(result);
    }
}
