import java.util.*;

public class 카카오2022인턴십_등산코스정하기4 {
    public static void main(String[] args) {
        Solution sol = new Solution();
//        int n = 7;
//        int[][] paths = new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}};
//        int[] gates = new int[]{3, 7};
//        int[] summits = new int[]{1, 5};
//        int n = 7;
//        int[][] paths = new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
//        int[] gates = new int[]{1};
//        int[] summits = new int[]{2, 3, 4};
        int n = 6;
        int[][] paths = new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = new int[]{1, 3};
        int[] summits = new int[]{5};
        System.out.println(Arrays.toString(sol.solution(n, paths, gates, summits)));
    }

    static class Solution {
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            ArrayList<Link> links = new ArrayList<>();

            for (int i = 0; i < paths.length; i++) {
                Link tempLink = new Link();
                tempLink.from = paths[i][0];
                tempLink.to = paths[i][1];
                tempLink.cost = paths[i][2];

                links.add(tempLink);
            }

            int summit = 0;
            int intensity = Integer.MAX_VALUE;

            int[] costs = new int[0];
            for (int i = 0; i < gates.length; i++) {
                costs = dijkstra(n, gates[i],summits, links);
                for (int j = 0; j < summits.length; j++) {
                    if (costs[summits[j]] < intensity) {
                        summit = summits[j];
                        intensity = costs[summits[j]];
                    }
                }
            }


            return new int[]{summit, intensity};
        }

        class Node implements Comparable<Node> {
            int id;
            int cost;

            public Node(int id, int cost) {
                this.id = id;
                this.cost = cost;
            }

            @Override
            public int compareTo(Node other) {
                if (this.cost < other.cost) {
                    return -1;
                }
                return 1;
            }
        }

        class Link {
            int from;
            int to;
            int cost;
        }

        int getCost(ArrayList<Link> links, int from, int to) {
            for (Link link : links) {
                if (link.to == to && link.from == from) {
                    return link.cost;
                } else if (link.to == from && link.from == to) {
                    return link.cost;
                }
            }
            return Integer.MAX_VALUE;
        }

        List<Integer> getLinkedNode(ArrayList<Link> links, int node) {
            List<Integer> list = new ArrayList<>();
            for (Link link : links) {
                if (link.from == node) {
                    list.add(link.to);
                } else if (link.to == node) {
                    list.add(link.from);
                }
            }
            return list;
        }

        int[] dijkstra(int n, int from,int[] summits, ArrayList<Link> links) {
            int[] costs = new int[n + 1];
            Arrays.fill(costs, Integer.MAX_VALUE);
            int[] costs2 = new int[n + 1];
            boolean[] visited = new boolean[n + 1];

            Queue<Node> q = new PriorityQueue();
            costs[from] = 0;
            Node start = new Node(from, costs[from]);
            q.add(start);
            int max = 0;

            while (!q.isEmpty()) {
                int cur = q.poll().id;
                if (visited[cur] == true) continue;
                max = Math.max(costs[cur], max);
                costs2[cur] = max;
                visited[cur] = true;
                if (isContain(summits,cur)) continue;
                // cur 와 연결된 거리 업데이트
                List<Integer> linked = getLinkedNode(links, cur);
                for (int i = 0; i < linked.size(); i++) {
                    int linkedNode = linked.get(i);
                    if (visited[linkedNode]) continue;
                    if (costs[linkedNode] > getCost(links, cur, linkedNode)) {
                        costs[linkedNode] = getCost(links, cur, linkedNode);
                        q.add(new Node(linkedNode, costs[linkedNode]));
                    }
                }
            }
            return costs2;
        }
        boolean isContain(int[] arr, int a){
            for (int i = 0; i < arr.length; i++) {
                if (arr[i]==a) return true;
            }
            return false;
        }

    }
}
