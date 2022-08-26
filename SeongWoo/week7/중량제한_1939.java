package SeongWoo.week7;

import java.util.*;

public class 중량제한_1939 {

    public static class Node {
        int number;
        boolean check = false;
        //key로 이어지는 노드(간선)와 value로 중량을 담은 Map
        HashMap<Integer, Integer> edgeMap = new HashMap<>();

        public Node(int number) {
            this.number = number;
        }
    }

    //weightArray을 이분탐색한다.
    //weightArray를 이분탐색하며 선택한 중량을 bfs 메서드에 넣어서 true가 반환(선택된 중량이 허용되는 루트가 있다는 뜻)되면 오른쪽(더 무거운 중량)을 탐색한다.
    public static int binarySearch(int[] weightArray, int start, int end, ArrayList<Node> nodeList) {
        int left = 0;
        int right = weightArray.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            initNodeList(nodeList);
            boolean flag = bfs(nodeList.get(start), nodeList, end, weightArray[mid]);

            if (flag) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static void initNodeList(ArrayList<Node> nodeList) {
        for (Node node : nodeList) {
            node.check = false;
        }
    }

    //bfs로 startNode부터 end까지를 탐색한다. end를 탐색하지 못하면 false를 반환
    //매개변수의 weight보다 nextNode로가는 간선의 중량이 적으면 해당 간선은 탐색하지 않는다.
    public static boolean bfs(Node startNode, ArrayList<Node> nodeList, int end, int weight) {
        Queue<Node> queue = new LinkedList<>();
        startNode.check = true;
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            HashMap<Integer, Integer> edgeMap = node.edgeMap;
            for (Integer to : edgeMap.keySet()) {
                if (nodeList.get(to).check || edgeMap.get(to) < weight) {
                    continue;
                }
                Node nextNode = nodeList.get(to);
                if (nextNode.number == end) {
                    return true;
                }
                nextNode.check = true;
                queue.offer(nextNode);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeSize = scanner.nextInt();
        int edgeSize = scanner.nextInt();

        //중량을 담는 배열
        int[] weightArray = new int[edgeSize];
        //노드를 담는 리스트
        ArrayList<Node> nodeList = new ArrayList<>();
        for (int i = 0; i <= nodeSize; i++) {
            nodeList.add(new Node(i));
        }

        for (int i = 1; i <= edgeSize; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            nodeList.get(from).edgeMap.put(to, weight);
            nodeList.get(to).edgeMap.put(from, weight);
            weightArray[i - 1] = weight;
        }

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        Arrays.sort(weightArray);

        int result = binarySearch(weightArray, start, end, nodeList);

        System.out.println(weightArray[result]);
    }
}
