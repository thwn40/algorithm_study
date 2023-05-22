package SeongWoong.week36;

import java.util.*;

public class 그래프_방의개수 {
    public static void main(String[] args) {
//        int[] arrows = new int[]{6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
        int[] arrows = new int[]{1, 1, 4, 4, 6, 6, 1, 7, 3};
//        int[] arrows = new int[]{2, 7, 2, 5, 0};
        Solution solution = new Solution();
        System.out.println(solution.solution(arrows));

    }

    static class Solution {
        public int solution(int[] arrows) {
            int answer = 0;
            int[] dy = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
            int[] dx = new int[]{0, 1, 1, 1, 0, -1, -1, -1,};
            Map<Integer, List<int[]>> map = new HashMap<>();

            int cy = 0;
            int cx = 0;
            List<int[]> list = new ArrayList<>();
            int[] arr = new int[]{cx, 8}; // {cy}
            list.add(arr);
            map.put(0, list);
            for (int i = 0; i < arrows.length; i++) {
                int curArrow = arrows[i];
                for (int t = 0; t < 2; t++) {
                    int ny = cy + dy[curArrow];
                    int nx = cx + dx[curArrow];
                    if (map.containsKey(ny)) {
                        list = map.get(ny);
                        for (int j = 0; j < list.size(); j++) {
                            arr = list.get(j);
                            if (arr[0] == nx) {
                                if (arr[1] != curArrow && Math.abs(arr[1] - curArrow) != 4) {
                                    answer++;
                                    break;
                                }
                            }
                        }
                    } else {
                        list = new ArrayList<>();
                    }
                    list.add(new int[]{nx, curArrow});
                    map.put(ny, list);
                    list = map.get(nx);
                    list.add(new int[]{ny, curArrow > 3 ? curArrow-4 : 4-curArrow});
                    map.put(nx, list);
                    cy = ny;
                    cx = nx;
                }
            }
            return answer;
        }
    }
    class Solution2 {
        class Pair {
            public int x;
            public int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int hashCode() {
                return Objects.hash(x,y);
            }

            public boolean equals(Object o) {
                return this.x == ((Pair) o).x && this.y == ((Pair) o).y;
            }
        }

        public int solution(int[] arrows) {

            // 변수 선언
            int cnt = 0;

            // 방향 관련 배열 선언
            Pair pointHC = new Pair(0, 0);
            int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
            int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };

            // 방문 여부 관련 선언
            // key = 시작 node의 hashcode, value = 연결된 node들의 hashcode
            HashMap<Pair, ArrayList<Pair>> visitied = new HashMap<>();

            // 로직 처리
            for (int arrow : arrows) {
                for (int i = 0; i <= 1; i++) { // 교차점 처리를 위한 스케일업(반복 2번)

                    // 이동 진행
                    Pair newPointHC = new Pair(pointHC.x + dx[arrow], pointHC.y + dy[arrow]);

                    // 처음 방문하는 경우 = map에 키값이 없는 경우
                    if (!visitied.containsKey(newPointHC)) {
                        // 리스트에 연결점 추가
                        visitied.put(newPointHC, makeEdgeList(pointHC));

                        if(visitied.get(pointHC) == null) { // 기존점도 없다면 업데이트
                            visitied.put(pointHC, makeEdgeList(newPointHC));
                        } else { // 기존점이 있다면 추가하기
                            visitied.get(pointHC).add(newPointHC);
                        }

                        // 재방문했고 간선을 처음 통과하는 경우
                    } else if (visitied.containsKey(newPointHC) && !(visitied.get(newPointHC).contains(pointHC))) {
                        visitied.get(newPointHC).add(pointHC);
                        visitied.get(pointHC).add(newPointHC);
                        cnt++;
                    }

                    // 이동 완료
                    pointHC = newPointHC;
                }
            }

            return cnt;
        }

        // 밸류값에 넣기 위한 리스트 만들기
        public ArrayList<Pair> makeEdgeList(Pair pointHC) {
            ArrayList<Pair> edge = new ArrayList<>();
            edge.add(pointHC);
            return edge;
        }
    }
}
