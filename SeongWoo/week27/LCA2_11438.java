package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LCA2_11438 {

    private static class Node {
        int number;
        boolean check = false;
        int depth = 0;
        //parent[i] => Node의 2^i번째 부모
        Node[] parent;
        List<Node> linkedNodeList = new ArrayList<>();

        public Node(int number, Node[] parent) {
            this.number = number;
            this.parent = parent;
        }
    }

    private static void bfs(Node fistNode) {
        Queue<Node> queue = new LinkedList<>();
        fistNode.check = true;
        queue.offer(fistNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (Node nextNode : currentNode.linkedNodeList) {
                if (nextNode.check) {
                    continue;
                }

                nextNode.depth = currentNode.depth + 1;
                nextNode.parent[0] = currentNode;
                nextNode.check = true;
                queue.offer(nextNode);
            }
        }
    }

    private static void dp(Node[] nodeArr) {

        //j => parent의 인덱스.
        //j가 2^j < node를 만족할때까지 탐색하며 값을 구한다.(가장 깊은 Node의 parent의 값을 구하기 위함)
        for (int j = 1; (1<<j) < nodeArr.length; j++) {
            for (int i = 1; i < nodeArr.length; i++) {
                Node node = nodeArr[i];
                //2^(j - 1)번째 부모가 없는 경우와 2^(j - 1)번째 부모의 j - 1번째 부모가 없을 경우는 넘어간다
                if (node.parent[j - 1] == null ||
                        nodeArr[node.parent[j - 1].number].parent[j - 1] == null) {
                    continue;
                }
                //점화식 => Node의 2^j번째 부모는 Node의 2^(j - 1)번째 부모의 2^(j - 1)번째 부모와 같다.
                node.parent[j] = nodeArr[node.parent[j - 1].number].parent[j - 1];
            }
        }
    }

    private static int lca(Node deepNode, Node shallowNode) {
        if (deepNode.depth < shallowNode.depth) {
            Node temp = deepNode;
            deepNode = shallowNode;
            shallowNode = temp;
        }

        //log => 깊은 Node의 깊이를 표현할 수 있는 2의 승수
        //예) deepNode.depth가 13이면 log는 8이 되어야함(8 + 4 + 1 로 표현 가능)
        int log = 1;
        while ((1<<log) <= deepNode.depth) {
            log++;
        }
        log--;

        for (int i = log; i >= 0; i--) {
            //deepNode의 깊이가 shallowNode와 같아질 때까지 깊이를 올려준다(parent배열을 사용)
            if (deepNode.depth - (1<<i) >= shallowNode.depth) {
                deepNode = deepNode.parent[i];
            }
        }

        //deepNode와 shallowNode가 같을 경우 바로 return(불필요한 탐색을 없앰)
        if (deepNode.number == shallowNode.number) {
            return deepNode.number;
        } else {
            for (int i = log; i >= 0; i--) {
                if (deepNode.parent[i] != null &&
                        deepNode.parent[i].number != shallowNode.parent[i].number) {
                    deepNode = deepNode.parent[i];
                    shallowNode = shallowNode.parent[i];
                }
            }
        }
        return deepNode.parent[0].number;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        Node[] nodeArr = new Node[nodeSize + 1];

        int log = 1;
        while ((1 << log) <= nodeSize) {
            log++;
        }

        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(i, new Node[log]);
        }

        for (int i = 1; i < nodeArr.length - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            nodeArr[from].linkedNodeList.add(nodeArr[to]);
            nodeArr[to].linkedNodeList.add(nodeArr[from]);
        }

        st = new StringTokenizer(br.readLine());
        int caseSize = Integer.parseInt(st.nextToken());

        bfs(nodeArr[1]);
        dp(nodeArr);

        while (caseSize-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int deepNumber = Integer.parseInt(st.nextToken());
            int shallowNumber = Integer.parseInt(st.nextToken());

            int ancestor = lca(nodeArr[deepNumber], nodeArr[shallowNumber]);
            System.out.println(ancestor);
        }
    }
}
