package SeongWoo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실_배정 {

    public static class Lecture {

        int startTime;
        int endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int lectureSize = Integer.parseInt(st.nextToken());

        List<Lecture> lectureList = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < lectureSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            lectureList.add(new Lecture(startTime, endTime));
        }

        lectureList.sort((a, b) -> a.startTime == b.startTime ? a.endTime - b.endTime : a.startTime - b.startTime);
        queue.offer(lectureList.get(0).endTime);

        for (int i = 1; i < lectureList.size(); i++) {
            Lecture lecture = lectureList.get(i);
            if (lecture.startTime >= queue.peek()) {
                queue.poll();
            }
            queue.offer(lecture.endTime);
        }

        System.out.println(queue.size());
    }
}
