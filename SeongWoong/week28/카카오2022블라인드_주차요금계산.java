package SeongWoong.week28;

import java.util.ArrayList;
import java.util.Arrays;

public class 카카오2022블라인드_주차요금계산 {
    public static void main(String[] args) {
        int[] fees = new int[]{180, 5000, 10, 600};
        String[] records = new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
//        int[] fees = new int[]{1, 461, 1, 10};
//        String[] records = new String[]{"00:00 1234 IN"};
        Solution solution = new Solution();
        int[] answer = solution.solution(fees, records);
        System.out.println(Arrays.toString(answer));
        //11:48 시작 12:19 종료
    }
    static class Solution {
        public int[] solution(int[] fees, String[] records) {
            ArrayList<Integer> list = new ArrayList<>();

            int[] time = new int[10000];
            boolean[] check = new boolean[10000];
            for (int i = 0; i < records.length; i++) {
                String[] record = records[i].split(" ");
                int min = hourToMinute(record[0]);
                int num = Integer.parseInt(record[1]);
                String inout = record[2];
                if (inout.equals("IN")) {
                    time[num] -= min;
                    check[num] = true;
                } else {
                    time[num] += min;
                    check[num] = true;
                }
            }
            for (int i = 0; i < time.length; i++) {
                if (check[i]) {
                    if (time[i]<=0){
                        time[i] += 1439;
                    }
                    list.add(checkFee(time[i],fees));
                }
            }
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }

        private int checkFee(int time,int[] fees) {
            int answer = 0;
            int A = fees[0]; // 기본 시간
            int B = fees[1]; // 기본 요금
            int C = fees[2]; // 단위 시간
            int D = fees[3]; // 단위 요금
            answer += B;
            time-=A;
            if (time > 0) {
                double temp = (double)time / C;
                temp = Math.ceil(temp);
                answer += temp * D;
            }
            return answer;
        }

        public int hourToMinute(String str) {
            int hour = Integer.parseInt(str.split(":")[0])*60;
            int minute = Integer.parseInt(str.split(":")[1]);
            return hour + minute;
        }
    }
}
