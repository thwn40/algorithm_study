package SeongWoo.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 숨바꼭질4_13913 {

    static int[] moveSet = {1, -1, 2};

    private static class Node {

        int location;
        int time;
        Node beforeNode = null;

        public Node(int location, int time) {
            this.location = location;
            this.time = time;
        }
    }

    private static Node bfs(Node node, int k) {

        boolean[] check = new boolean[100001];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        check[node.location] = true;

        while (!queue.isEmpty()) {

            Node currentNode = queue.poll();
            int currentLocation = currentNode.location;
            int currentTime = currentNode.time;

            if (currentLocation == k) {
                return currentNode;
            }

            for (int i = 0; i < 3; i++) {

                int nextLocation;
                if (i == 2) {
                    nextLocation = currentLocation * moveSet[i];
                } else {
                    nextLocation = currentLocation + moveSet[i];
                }

                if (nextLocation < 0 || nextLocation > 100000 || check[nextLocation]) {
                    continue;
                }

                Node nextNode = new Node(nextLocation, currentTime + 1);
                nextNode.beforeNode = currentNode;

                queue.offer(nextNode);
                check[nextLocation] = true;
            }
        }

        return null;
    }

    private static void printRoute(List<Integer> route, Node result, int n) {

        System.out.println(result.time);

        if (result.time == 0) {
            System.out.println(n);
            return;
        }

        route.add(result.location);
        Node beforeNode = result.beforeNode;
        while (beforeNode != null) {
            route.add(beforeNode.location);
            beforeNode = beforeNode.beforeNode;
        }

        Collections.reverse(route);
        for (Integer location : route) {
            System.out.print(location + " ");
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> route = new ArrayList<>();

        Node node = new Node(n, 0);

        Node result = bfs(node, k);

        printRoute(route, result, n);
    }
}
