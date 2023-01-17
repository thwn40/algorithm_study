package SeongWoo.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 숨바꼭질3_13549 {

    static int[] moveSet = {-1, 1, 2};

    private static class Node {

        int number;
        int time;

        public Node(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }

    private static Node bfs(Node firstNode, int dist, boolean[] check) {

        Deque<Node> deque = new LinkedList<>();
        deque.offer(firstNode);
        check[firstNode.number] = true;

        while (!deque.isEmpty()) {

            Node currentNode = deque.poll();
            check[currentNode.number] = true;

            if (currentNode.number == dist) {
                return currentNode;
            }

            for (int i = 0; i < 3; i++) {

                int nextNumber;
                if (i == 2) {
                    nextNumber = currentNode.number * moveSet[i];
                } else {
                    nextNumber = currentNode.number + moveSet[i];
                }

                if (nextNumber < 0 || nextNumber > 100000 || check[nextNumber]) {
                    continue;
                }

                if (i == 2) {
                    deque.push(new Node(nextNumber, currentNode.time));
                } else {
                    deque.offer(new Node(nextNumber, currentNode.time + 1));
                }
            }
        }

        return null;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] check = new boolean[100001];
        Node node = new Node(n, 0);

        Node result = bfs(node, k, check);

        System.out.println(result.time);
    }
}
