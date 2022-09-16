package SeongWoo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 회의실배정_1931 {

    private static class Meeting {
        long startTime;
        long endTime;

        public Meeting(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    private static void sortMeeting(Meeting[] meetingArr) {
        Arrays.sort(meetingArr, (m1, m2) -> {
            if (m1.startTime < m2.startTime) {
                return -1;
            } else if (m1.startTime == m2.startTime) {
                if (m1.endTime < m2.endTime) {
                    return -1;
                }
                return 0;
            }
            return 1;
        });
    }

    private static Stack<Meeting> settingMeet(int meetingSize, Meeting[] meetingArr) {
        Stack<Meeting> stack = new Stack<>();

        Meeting firstMeeting = meetingArr[0];
        stack.push(firstMeeting);

        for (int i = 1; i < meetingSize; i++) {
            Meeting stackedMeeting = stack.peek();
            Meeting nextMeeting = meetingArr[i];

            if (nextMeeting.startTime >= stackedMeeting.endTime) {
                stack.push(nextMeeting);
                continue;
            }

            if (nextMeeting.endTime < stackedMeeting.endTime) {
                stack.pop();
                stack.push(nextMeeting);
            }
        }
        return stack;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int meetingSize = Integer.parseInt(br.readLine());

        Meeting[] meetingArr = new Meeting[meetingSize];

        for (int i = 0; i < meetingSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            long startTime = Long.parseLong(st.nextToken());
            long endTime = Long.parseLong(st.nextToken());
            meetingArr[i] = new Meeting(startTime, endTime);
        }

        sortMeeting(meetingArr);

        Stack<Meeting> stack = settingMeet(meetingSize, meetingArr);

        System.out.println(stack.size());
    }
}
