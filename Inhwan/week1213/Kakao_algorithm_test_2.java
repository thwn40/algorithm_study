package Inhwan.week1213;

public class Kakao_algorithm_test_2 {

    public static void main(String[] args) {
//        int cap = 4;
//        int n = 5;
//        int[] deliveries = {1,0,3,1,2};
//        int[] pickups = {0,3,0,4,0};

        int cap = 2;
        int n = 7;
        int[] deliveries = {1,0,2,0,1,0,2};
        int[] pickups = {0,2,0,1,0,2,0};

        System.out.println(solution(cap, n, deliveries, pickups));
    }

    static int solution(int cap, int n, int[] deliveries, int[] pickups) {
        int result = 0;

        int deliverSum = 0;
        int pickupSum = 0;

        for (int i = 0; i < n; i++) {
            deliverSum += deliveries[i];
            pickupSum += pickups[i];
        }

        int times = Math.max(deliverSum, pickupSum);
        times += n-1;
        times /= cap;

        int indexOfDeliver = n-1;
        int indexOfPickup = n-1;

        for (int i = 0; i < times; i++) {
            int deliver = cap;
            int pickup = cap;

            int distance = 0;

            while (deliver > 0 && indexOfDeliver >= 0) {
                if (deliveries[indexOfDeliver] > 0) {
                    if (deliver == cap) {
                        distance = indexOfDeliver + 1;
                    }

                    deliveries[indexOfDeliver]--;
                    deliver--;
                } else {
                    indexOfDeliver--;
                }
            }

            while (pickup > 0 && indexOfPickup >= 0) {
                if (pickups[indexOfPickup] > 0) {
                    if (pickup == cap) {
                        distance = Math.max(distance, indexOfPickup + 1);
                    }
                    pickups[indexOfPickup]--;
                    pickup--;
                } else {
                    indexOfPickup--;
                }
            }

            result += distance*2;
        }

        return result;
    }
}
