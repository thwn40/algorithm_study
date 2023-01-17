package SeongWoo.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DSLR_9019 {

    static char[] operatorSet = {'D', 'S', 'L', 'R'};

    private static class Node {

        int number;
        Integer beforeNumber;
        Character beforeCalculation;

        public Node(int number) {
            this.number = number;
        }

        public Node(int number, Integer beforeNumber, Character beforeCalculation) {
            this.number = number;
            this.beforeNumber = beforeNumber;
            this.beforeCalculation = beforeCalculation;
        }
    }

    //문제의 연산을 처리하는 메서드
    private static Integer calculator(char operator, int number) {

        Integer result = 0;

        switch (operator) {
            case 'D':
                result = (number * 2) % 10000;
                break;
            case 'S':
                result = number == 0 ? 9999 : number - 1;
                break;
            case 'L':
                result = (number % 1000) * 10 + (number / 1000);
                break;
            case 'R':
                result = (number % 10) * 1000 + (number / 10);
        }

        return result;
    }

    private static Node bfs(Node firstNode, int dist, Map<Integer, Node> checkMap) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(firstNode);
        checkMap.put(firstNode.number, firstNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.number == dist) {
                return currentNode;
            }

            for (int i = 0; i < 4; i++) {
                Integer nextNumber = calculator(operatorSet[i], currentNode.number);

                if (checkMap.containsKey(nextNumber)) {
                    continue;
                }

                Node nextNode = new Node(nextNumber, currentNode.number, operatorSet[i]);
                queue.offer(nextNode);
                checkMap.put(nextNode.number, nextNode);
            }
        }

        return null;
    }

    //마지막 노드로 부터 결과를 출력하는 메서드
    private static String getResult(Node node, Map<Integer, Node> checkMap) {

        StringBuilder stringBuilder = new StringBuilder();

        while (node.beforeCalculation != null) {
            stringBuilder.insert(0, node.beforeCalculation);

            node = checkMap.get(node.beforeNumber);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int caseCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < caseCount; i++) {

            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            Node firstNode = new Node(start);
            Map<Integer, Node> checkMap = new HashMap<>();

            Node lastNode = bfs(firstNode, dist, checkMap);

            String result = getResult(lastNode, checkMap);

            System.out.println(result);
        }
    }
}
