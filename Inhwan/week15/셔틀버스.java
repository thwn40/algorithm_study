package Inhwan.week15;

import java.util.Arrays;

class 셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {

        int[] table = Arrays.stream(timetable).mapToInt(str -> toInt(str)).toArray();

        Arrays.sort(table);
        int index = 0;

        int departTime = 60*9;
        int passengers = 0;

        for (int i = 0; i < n; i++) {
            passengers = 0;

            while (index < table.length &&
                    table[index] <= departTime &&
                    passengers < m) {
                index++;
                passengers++;
            }

            departTime += t;
        }

        if (passengers < m) {
            departTime -= t;
            return toString(departTime);
        }

        index--;
        int last = table[index];
        last--;

        return toString(last);
    }

    int toInt(String time) {
        int hour = Integer.parseInt(time.substring(0,2));
        int minute = Integer.parseInt(time.substring(3,5));

        return 60 * hour + minute;
    }

    String toString(int time) {
        String hour = "" + (time/60);
        String minute = "" + (time%60);

        if (hour.length() < 2) hour = 0 + hour;
        if (minute.length() < 2) minute = 0 + minute;
        return hour + ":" + minute;
    }
}