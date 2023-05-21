package SeongWoo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 강의실_배정_실패 {

    public static class Lecture {
        int startTime;
        int endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }


    public static int getRoomCount(List<Lecture> lectureList) {

        int count = 0;

        while (!lectureList.isEmpty()) {
            count++;
            Lecture currentLecture = lectureList.get(0);
            lectureList.remove(0);

            for (int i = 0; i < lectureList.size(); i++) {
                Lecture afterLecture = lectureList.get(i);
                if (currentLecture.endTime > afterLecture.startTime) {
                    continue;
                }
                currentLecture = afterLecture;
                lectureList.remove(i);
                i--;

            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int lectureSize = Integer.parseInt(st.nextToken());

        List<Lecture> lectureList = new ArrayList<>();

        for (int i = 0; i < lectureSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            lectureList.add(new Lecture(startTime, endTime));
        }

        lectureList.sort((a, b) -> a.startTime - b.startTime);

        int roomCount = getRoomCount(lectureList);

        System.out.println(roomCount);
    }
}
