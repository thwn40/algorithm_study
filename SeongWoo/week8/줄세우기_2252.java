package SeongWoo.week8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 줄세우기_2252 {

    //node를 나타내는 클래스
    private static class Node {
        int number;
        //진입차수
        int linkEdge = 0;
        //간선
        ArrayList<Node> edge = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    //위상정렬된 값들을 sortResult에 넣고 반환하는 메서드
    private static ArrayList<Integer> topologySort(Node[] nodeArr) {
        //위상정렬된 값들이 들어갈 리스트
        ArrayList<Integer> sortResult = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        //queue에 초기값으로 노드방향 간선 수가 0인 값들을 넣는다.
        for (int i = 1; i < nodeArr.length; i++) {
            if (nodeArr[i].linkEdge == 0) {
                queue.offer(nodeArr[i]);
                sortResult.add(nodeArr[i].number);
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            //반복문을 돌면서 node와 연결된 간선을 지운다.
            //간선을 지운다 = 간선으로 이어진 node의 linkEdge(진입차수)에서 1을 빼줌
            for (int i = 0; i < node.edge.size(); i++) {
                Node linkNode = node.edge.get(i);
                linkNode.linkEdge--;
                if (linkNode.linkEdge == 0) {
                    queue.offer(linkNode);
                    sortResult.add(linkNode.number);
                }
            }
        }
        return sortResult;
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
            nodeArr[from].edge.add(nodeArr[to]);
            //진입차수 증가
            nodeArr[to].linkEdge++;
        }

        ArrayList<Integer> sortResult = topologySort(nodeArr);
        sortResult.forEach(s -> System.out.print(s + " "));
    }
}
