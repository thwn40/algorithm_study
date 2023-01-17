package SeongWoo.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이모티콘_14226 {

    //화면과 클립보드, 시간 정보를 갖는 클래스
    private static class Node {

        int screen;
        int clip;
        int time;

        public Node(int screen, int clip, int time) {
            this.screen = screen;
            this.clip = clip;
            this.time = time;
        }

        public Node() {}
    }

    //다음 노드를 구하는 메서드
    private static Node getNextNode(Node node, int number) {

        Node nextNode = new Node();

        switch (number) {
            case 0:
                nextNode.clip = node.screen;
                nextNode.screen = node.screen;
                break;
            case 1:
                if (node.clip == 0) {
                    return null;
                }
                nextNode.screen = node.screen + node.clip;
                nextNode.clip = node.clip;
                break;
            case 2:
                if (node.screen == 0) {
                    return null;
                }
                nextNode.screen = node.screen - 1;
                nextNode.clip = node.clip;
                break;
        }

        nextNode.time = node.time + 1;
        return nextNode;
    }

    private static Node bfs(Node firstNode, int dist, Node[][] nodeArr) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(firstNode);
        nodeArr[firstNode.screen][firstNode.clip] = firstNode;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.screen == dist) {
                return currentNode;
            }

            for (int i = 0; i < 3; i++) {
                Node nextNode = getNextNode(currentNode, i);

                //화면은 1000을 넘을 필요가 없다.
                //복사로 1000을 넘겨서 1씩 빼주는 것은 1씩 빼고 복사하는 것과 같기 때문
                if (nextNode == null || nextNode.screen < 0 || nextNode.screen > 1000
                        || nodeArr[nextNode.screen][nextNode.clip] != null) {
                    continue;
                }

                queue.offer(nextNode);
                nodeArr[nextNode.screen][nextNode.clip] = nextNode;
            }
        }

        return null;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());

        //screen을 행으로 clip을 열으로 표현하는 배열
        Node[][] nodeArr = new Node[1001][1001];

        Node firstNode = new Node(1, 0, 0);

        Node result = bfs(firstNode, dist, nodeArr);

        System.out.println(result.time);
    }
}
