import java.util.*;

public class 카카오2022인턴십_등산코스정하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
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
            int intensity = Integer.MIN_VALUE;

            for (int i = 0; i < gates.length; i++) {
                for (int j = 0; j < summits.length; j++) {
                    int tempSummit = summits[j];
                    int[] cost = dijkstra(n, gates[i], tempSummit,links);

                    for (int c = 1; c < cost.length; c++) {
                        if (cost[c] > intensity){
                            intensity = cost[c];
                            summit = tempSummit;
                        }
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
            return -1;
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

        int[] dijkstra(int n, int from, int to, ArrayList<Link> links) {
            int[] costs = new int[n+1];
            Arrays.fill(costs, Integer.MAX_VALUE);
            boolean[] visited = new boolean[n+1];

            Queue<Node> q = new PriorityQueue();
            costs[from] = 0;
            Node start = new Node(from, costs[from]);
            q.add(start);

            while (!q.isEmpty()) {
                int cur = q.poll().id;
                if (visited[cur] == true) continue;
                visited[cur] = true;
                // cur 와 연결된 거리 업데이트
                List<Integer> linked = getLinkedNode(links, cur);
                for (int i = 0; i < linked.size(); i++) {
                    int linkedNode = linked.get(i);
//                    if (costs[linkedNode] == Integer.MAX_VALUE) {
//                        costs[linkedNode] = getCost(links, cur, linkedNode);
//                    } else {
//                        if (visited[linkedNode]) continue;
//                        costs[linkedNode] += getCost(links, cur, linkedNode);
//                    }
                    if (visited[linkedNode]) continue;
                    if (costs[linkedNode] > costs[cur] + getCost(links, cur, linkedNode)){
                        costs[linkedNode] = costs[cur] + getCost(links, cur, linkedNode);
                    }
                    q.add(new Node(linkedNode, costs[linkedNode]));
                }
            }
            return costs;
        }

    }
}
