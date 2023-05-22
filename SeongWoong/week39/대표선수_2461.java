package SeongWoong.week39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 대표선수_2461 {
    static int N, M;
    static int count[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 학급 수
        M = Integer.parseInt(st.nextToken());   // 학생 수
        count = new int[N]; // 각 학급의 학생이 몇 명 사용됐는가
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                students.add(new Student(i, Integer.parseInt(st.nextToken())));
            }
        }
        Collections.sort(students); // 능력치로 정렬

        int left = 0;
        int right = 0;
        int answer = Integer.MAX_VALUE;

        while (left < N * M - 1 && right < N * M - 1) {
            while (right < N * M - 1) { //모든 학급이 포함되도록 right 변경
                count[students.get(right++).num]++;
                if (check()) break;
            }
            while (count[students.get(left).num] > 1) { //모든 학급이 포함되도록 left 변경
                count[students.get(left++).num]--;
            }
            if (check()) {  //모든 학급 포함시 값 갱신
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int i = left; i < right; i++) {
                    min = Math.min(min, students.get(i).stat);
                    max = Math.max(max, students.get(i).stat);
                }
                answer = Math.min(answer, max - min);
            }
            count[students.get(left++).num]--;  // left 변경
        }
        System.out.println(answer);
    }

    public static boolean check() { //모든 학급이 포함되었는지 확인하는 함수
        for (int cnt : count) {
            if (cnt == 0) return false;
        }
        return true;
    }

    static class Student implements Comparable<Student> {
        int num;
        int stat;

        public Student(int num, int stat) {
            this.num = num;
            this.stat = stat;
        }

        @Override
        public int compareTo(Student o) {
            return this.stat - o.stat;
        }
    }
}