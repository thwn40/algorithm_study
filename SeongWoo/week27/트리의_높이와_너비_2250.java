package SeongWoo.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의_높이와_너비_2250 {

    static int order = 1;

    private static void inOrder(int index, int[][] nodeArr, Map<Integer, List<Integer>> nodeMap, int depth) {
        if (index == -1) {
            return;
        }

        int[] node = nodeArr[index];
        inOrder(node[0], nodeArr, nodeMap, depth + 1);

        //깊이에 해당하는 리스트가 없으면 추가, 있으면 반환
        List<Integer> nodeList = nodeMap.computeIfAbsent(depth, k -> new ArrayList<>());
        nodeList.add(order++);

        inOrder(node[1], nodeArr, nodeMap, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        int[][] nodeArr = new int[nodeSize + 1][2];
        int[] nodeParent = new int[nodeSize + 1];
        HashMap<Integer, List<Integer>> nodeMap = new HashMap<>();
        int maxDepth = 0;
        int maxWidth = 0;
        int root = 0;

        for (int i = 1; i < nodeArr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (left != -1) {
                nodeParent[left] = node;
            }
            if (right != -1) {
                nodeParent[right] = node;
            }

            nodeArr[node][0] = left;
            nodeArr[node][1] = right;
        }

        //루트 노드 찾기
        for (int i = 1; i < nodeParent.length; i++) {
            if (nodeParent[i] == 0) {
                root = i;
            }
        }

        inOrder(root, nodeArr, nodeMap, 1);

        int depth = 0;
        while (nodeMap.get(++depth) != null) {
            int tempWidth = 0;
            List<Integer> nodeList = nodeMap.get(depth);

            if (nodeList.size() == 1) {
                tempWidth = 1;
            } else {
                //트리를 inOrder로 순회 시, 순서대로 값이 들어가므로 가장 앞과 뒤의 차이가 너비가된다.
                tempWidth = nodeList.get(nodeList.size() - 1) - nodeList.get(0) + 1;
            }

            if (tempWidth > maxWidth) {
                maxWidth = tempWidth;
                maxDepth = depth;
            }
        }

        System.out.println(maxDepth + " " + maxWidth);
    }
}
