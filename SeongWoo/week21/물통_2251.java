package SeongWoo.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class 물통_2251 {

    private static class Node {

        int[] bucketArr = new int[3];

        public Node(int a, int b, int c) {
            bucketArr[0] = a;
            bucketArr[1] = b;
            bucketArr[2] = c;
        }

        public Node() {}

        public Node getNextNode(int from, int to, int amount, int total) {

            Node nextNode = new Node();

            int nextFrom = this.bucketArr[from] - amount;
            int nextTo = this.bucketArr[to] + amount;

            for (int i = 0; i < 3; i++) {
                if (i == from) {
                    nextNode.bucketArr[i] = nextFrom;
                } else if (i == to) {
                    nextNode.bucketArr[i] = nextTo;
                } else {
                    nextNode.bucketArr[i] = total - nextFrom - nextTo;
                }
            }

            return nextNode;
        }
    }

    private static void bfs(Node firstNode, Node[][] nodeArr, int[] maxBucket, int total) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(firstNode);
        int[] firstBucketArr = firstNode.bucketArr;
        nodeArr[firstBucketArr[0]][firstBucketArr[2]] = firstNode;

        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();
            int[] currentBucketArr = currentNode.bucketArr;

            for (int i = 0; i < 3; i++) {
                for (int j = 1; j <= 2; j++) {
                    //물을 붓는 경우를 나머지를 사용해서 표현
                    int from = i;
                    int to = (i + j) % 3;

                    if (currentBucketArr[from] == 0 || currentBucketArr[to] == maxBucket[to]) {
                        continue;
                    }

                    int amount = Math.min(maxBucket[to] - currentBucketArr[to], currentBucketArr[from]);

                    Node nextNode = currentNode.getNextNode(from, to, amount, total);
                    int[] nextBucketArr = nextNode.bucketArr;

                    if (nodeArr[nextBucketArr[0]][nextBucketArr[2]] != null) {
                        continue;
                    }

                    queue.offer(nextNode);
                    nodeArr[nextBucketArr[0]][nextBucketArr[2]] = nextNode;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] maxBucket = new int[3];
        maxBucket[0] = Integer.parseInt(st.nextToken());
        maxBucket[1] = Integer.parseInt(st.nextToken());
        maxBucket[2] = Integer.parseInt(st.nextToken());

        //A를 행 C를 열으로 표현한다.
        //B는 A와 C로 유추할 수 있다.
        Node[][] nodeArr = new Node[maxBucket[0] + 1][maxBucket[2] + 1];

        Node firstNode = new Node(0, 0, maxBucket[2]);

        bfs(firstNode, nodeArr, maxBucket, maxBucket[2]);

        List<Integer> result = Arrays.stream(nodeArr[0])
                .filter(Objects::nonNull)
                .map(node -> node.bucketArr[2]).sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
