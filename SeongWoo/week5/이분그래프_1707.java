package SeongWoo.week5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 이분그래프_1707 {

    public static class Node {   //Node 정보를 담은 클래스
        int number;
        int team;   //이분그래프인지 판단하기 위한 flag. 1과 -1 두 팀으로 나뉜다.
        boolean check = false;
        ArrayList<Node> edgeList = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }

        public void setTeam(int team) {
            this.team = team;
        }
    }

    public static boolean bfs(Node[] nodes, int firstIndex) {   //bfs로 각 노드를 탐색하며, 연결된 노드가 같은 팀인지 확인한다.(연결된 노드가 같은 팀이면 이분그래프아님)
        Queue<Node> queue = new LinkedList<>();
        Node firstNode = nodes[firstIndex];
        firstNode.setTeam(1);   //초기 팀은 1으로 설정
        firstNode.check = true;
        queue.offer(firstNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();   //queue에서 node를 꺼낸다.

            for (int i = 0; i < node.edgeList.size(); i++) {   //node의 간선만큼 반복문을 돌린다.
                Node nextNode = node.edgeList.get(i);
                if (nextNode.check) {
                    if (node.team == nextNode.team) {   //node에 연결된 노드(=nextNode)가 node와 같은 팀이라면 이분그래프 아님
                        return false;
                    }
                } else {
                    nextNode.setTeam(node.team * -1);   //nextNode를 한번도 탐색하지 않았다면 node와 다른 team을 설정
                    nextNode.check = true;
                    queue.offer(nextNode);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();

        for (int i = 0; i < total; i++) {   //주어진 시행회수만큼 반복문을 돌린다.
            //초기화 시작
            boolean result = true;
            int nodeSize = scanner.nextInt();
            int edgeSize = scanner.nextInt();
            Node[] nodes = new Node[nodeSize + 1];
            for (int j = 0; j <= nodeSize; j++) {
                nodes[j] = new Node(i);
            }

            for (int k = 0; k < edgeSize; k++) {   //인접리스트 생성
                Node toNode = nodes[scanner.nextInt()];
                Node fromNode = nodes[scanner.nextInt()];
                toNode.edgeList.add(fromNode);
                fromNode.edgeList.add(toNode);
            }
            //초기화 끝

            for (int l = 1; l <= nodeSize; l++) {   //각 노드에 bfs를 적용해서 모든 노드를 탐색하고 이분그래프 여부를 판단한다.
                if (nodes[l].check) {
                    continue;
                }
                if (!bfs(nodes, l)) {   //bfs에 의해 이분그래프가 아니라고 판단되면 result를 false로 변경
                    result = false;
                }
            }

            if (result) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
