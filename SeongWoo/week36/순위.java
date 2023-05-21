package SeongWoo.week36;

import java.util.*;

public class 순위 {
    private static class Node {
        int number;
        HashSet<Integer> up = new HashSet<>();
        HashSet<Integer> down = new HashSet<>();
        boolean isGrade = false;
        boolean check = false;

        public Node(int number) {
            this.number = number;
        }

        public void checkGrade(int n) {
            if (n - 1 == up.size() + down.size()) {
                this.isGrade = true;
            }
        }

        public void extendUp(Node superNode) {
            this.up.addAll(superNode.up);
        }

        public void extendDown(Node superNode) {
            this.down.addAll(superNode.down);
        }
    }

    public void bfs(Node start, Node[] nodes, ArrayList<ArrayList<Integer>> edgeList, boolean isUp) {
        Queue<Node> queue = new LinkedList<>();
        start.check = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Integer number : edgeList.get(node.number)) {
                if (nodes[number].check) {
                    continue;
                }
                nodes[number].check = true;
                if (isUp) {
                    nodes[number].extendUp(node);
                } else {
                    nodes[number].extendDown(node);
                }
                queue.offer(nodes[number]);
            }
        }
    }

    public int solution(int n, int[][] results) {
        Node[] nodes = new Node[n + 1];
        ArrayList<ArrayList<Integer>> upEdge = new ArrayList<>();
        ArrayList<ArrayList<Integer>> downEdge = new ArrayList<>();

        //초기화
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node(i);
            upEdge.add(new ArrayList<>());
            downEdge.add(new ArrayList<>());
        }
        for (int[] result : results) {
            nodes[result[0]].down.add(result[1]);
            nodes[result[1]].up.add(result[0]);
            upEdge.get(result[0]).add(result[1]);
            downEdge.get(result[1]).add(result[0]);
        }
        //초기화

        //bfs로 각 노드를 탐색하며 진 정보와 이긴 정보를 전달
        for (int i = 1; i <= n; i++) {
            for (Node node : nodes) {
                node.check = false;
            }
            bfs(nodes[i], nodes, upEdge, true);

            for (Node node : nodes) {
                node.check = false;
            }
            bfs(nodes[i], nodes, downEdge, false);
        }

        //순위를 정할 수 있는지 판단
        for (int i = 1; i <= n; i++) {
            nodes[i].checkGrade(n);
        }

        long count = Arrays.stream(nodes)
                .filter(node -> node.isGrade)
                .count();
        return (int) count;
    }
}
