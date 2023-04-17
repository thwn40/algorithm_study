package 코딩테스트연습;

import java.util.*;

public class 동적계획법_N으로표현 {
    public static void main(String[] args) {
        int N = 2;
        int number = 55555;
        Solution solution = new Solution();
        System.out.println(solution.solution(N, number));
    }

    static class Solution {
        static List<Number> list;
        static boolean[] visit;

        public int solution(int N, int number) {
            int answer = Integer.MAX_VALUE;
            visit = new boolean[1000001];    // 중복체크    -> 만들수있느 최대수 999999 :6개 범위안에 들어가려면 나누기밖에 안됨 111111 :7개사용 또 나누기만 가능 12345 :8개 사용
                                             // 즉 6자리가 사용할 수 있는 최대의 수이다. 그 이상의 수를 만들어서 사용해봤자 9개를 사용하게 돼서 -1을 리턴해야한다.
            list = new ArrayList<>();   // 해당숫자로 만들 수 있는 기본 숫자 리스트 {5,55,555,5555,...}
            // 리스트 채우기
            int c = 1;
            int f = N;
            while (f < 1000000) {
                list.add(new Number(f,c++));
                visit[f] = true;
                f=f*10+N;
            }

            Queue<Number> q = new PriorityQueue<>();  //우선순위 큐로 설정하여 원하는 최솟값찾기
            q.addAll(list);
            // 큐에 계산 결과 넣으면서 원하는 값 찾기
            while(!q.isEmpty()){
                Number cur = q.poll();
                visit[cur.num] = true;
                if (cur.num == number) {
                    if (cur.cnt > 8) return -1;
                    return cur.cnt;
                }
                for (int i = 0; i < list.size(); i++) {
                    int next = 0;
                    for (int j = 0; j < 6; j++) {
                        if (j==5 && cur.num==0) continue;   // 0으로 나눌 수 없음
                        if (j==3 && list.get(i).num==0) continue;   // 0으로 나눌 수 없음
                        next = cal(cur.num, list.get(i).num,j); // 계산 결과
                        if (next>= visit.length || next<0 || visit[next]) continue; // 범위 밖 혹은 이미 방문

                        q.add(new Number(next, cur.cnt + i+1));
                    }
                }
            }
            return 0;
        }

        private int cal(int num, int num1, int j) {     // 계산
            if (j==0) return num + num1;
            if (j==1) return num - num1;
            if (j==2) return num * num1;
            if (j==3) return num / num1;
            if (j==4) return num1 - num;
            if (j==5) return num1 / num;
            return 0;
        }

        public static class Number implements Comparable<Number>{   // 각 숫자와 만드는데 필요한 횟수를 저장
            int num;
            int cnt;

            public Number(int num, int cnt) {
                this.num = num;
                this.cnt = cnt;
            }

            @Override
            public int compareTo(Number o) {
                return this.cnt-o.cnt;
            }
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@ 2번째 방법@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    class Solution2 {
        static Set<Integer>[] setArr;
        public int solution(int N, int number) {
            int answer = -1;
            setArr = new Set[9];
            int t = N;
            for(int i = 1; i < 9; i++) {
                setArr[i] = new HashSet<>();
                setArr[i].add(t);
                t = t * 10 + N;
            }
            for(int i = 1; i < 9; i++) {
                for(int j = 1; j < i; j++) {
                    cal(i,j);
                }
            }
            for(int i = 1; i < 9; i++) {
                if(setArr[i].contains(number)) {
                    answer = i;
                    break;
                }
            }
            return answer;
        }

        public static void cal(int i, int j){
            for(Integer a : setArr[j]) {
                for(Integer b : setArr[i - j]) {
                    setArr[i].add(a + b);
                    setArr[i].add(a - b);
                    setArr[i].add(b - a);
                    setArr[i].add(a * b);
                    if(b != 0) {
                        setArr[i].add(a / b);
                    }
                    if(a != 0) {
                        setArr[i].add(b / a);
                    }
                }
            }
        }
    }
}
