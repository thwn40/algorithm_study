package SeongWoo.week28;

import java.util.*;

public class 주차_요금_계산 {

    private static class Car {
        String number;
        String status;
        int inTime;
        int totalTime;
        int totalFee;

        public Car(String number) {
            this.number = number;
        }

        public void out(int outTime) {
            totalTime += outTime - inTime;
        }

        public void getTotalFee(int basicTime, int basicFee, int unitTime, int unitFee) {
            if (totalTime <= basicTime) {
                totalFee = basicFee;
            } else {
                int feeMinute = (int) Math.ceil((double)(totalTime - basicTime) / unitTime);
                totalFee = (feeMinute * unitFee) + basicFee;
            }
        }
    }

    private int convertMinute(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return hour * 60 + minute;
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, Car> carMap = new HashMap<>();
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        for (String record : records) {
            String[] split = record.split(" ");
            String time = split[0];
            String number = split[1];
            String status = split[2];

            Car car = carMap.computeIfAbsent(number, k -> new Car(number));
            int minute = convertMinute(time);
            car.status = status;

            if (status.equals("IN")) {
                car.inTime = minute;
            } else if (status.equals("OUT")) {
                car.out(minute);
            }
        }

        for (Car car : carMap.values()) {
            if (car.status.equals("IN")) {
                car.out(convertMinute("23:59"));
            }
            car.getTotalFee(basicTime, basicFee, unitTime, unitFee);
        }

        List<Car> carList = new ArrayList<>(carMap.values());
        carList.sort(Comparator.comparing(car -> car.number));

        int[] answer = new int[carList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = carList.get(i).totalFee;
        }

        return answer;
    }
}
