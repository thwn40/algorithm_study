import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 강의실배정_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
//        List<Sooup> list = new LinkedList<>(); // 대기중인 수업
        Sooup[] arr = new Sooup[N];
        Queue<Integer> q = new PriorityQueue<>(); // 진행중인 강의 실

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            arr[i] = new Sooup(S, T);
//            list.add(new Sooup(S, T));
        }
//        Collections.sort(list);
        Arrays.sort(arr);
//        q.add(list.get(0).end);
        q.add(arr[0].end);
//        for (int i = 1; i < list.size(); i++) { // 모든 수업을 순회
        for (int i = 1; i < arr.length; i++) { // 모든 수업을 순회
            int firstEnd = q.peek();            // 가장 일찍 끝나는 강의실의 종료시간과
//            Sooup next = list.get(i);         // 다음으로 해야하는 강의의 시작 시간을 비교하여
            Sooup next = arr[i];         // 다음으로 해야하는 강의의 시작 시간을 비교하여
            if (firstEnd <= next.start) {      // 시작 전에 이전 강의가 끝났으면
                q.poll();                     // 해당 강의실을 비우고
            }                                 // (안끝났으면 비우지 않고)새로운 강의실을 추가하기
            q.add(next.end);                      // 다음으로 해야하는 강의를 비운 강의실에 넣기
        }
        System.out.println(q.size());
    }

    static class Sooup implements Comparable<Sooup> {
        int start;
        int end;

        public Sooup(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Sooup s) {
            return this.start - s.start == 0 ? this.end - s.end : this.start - s.start;
        }
    }
}