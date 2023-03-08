import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 카카오2019겨울인턴십_호텔방배정 {
    public static void main(String[] args) {
        long k = 10;
        long[] room_number = new long[]{1, 3, 4, 1, 3, 1};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(k, room_number)));
        // 누적합 -> 배열 길이가 수용안된다.
        // 이분탐색은 어떨까 -> 마찬가지 배열길이가 안되고 원하는 값이 계속 변함
        // 값을 저장하는게 좋아보임
        //구현을해보자

    }
    static class Solution {
        public static long[] solution(long k, long[] room_number) {
            long[] answer = new long[room_number.length];

            List<Room> rooms = new ArrayList<>();
            for (int i = 0; i < room_number.length; i++) {
                long cur = room_number[i];
                long next = getNext(rooms, cur);
                rooms.add(new Room(next, next+1));
            }
            for (int i = 0; i < rooms.size(); i++) {
                answer[i] = rooms.get(i).cur;
            }
            return answer;
        }

        public static long getNext(List<Room> rooms, long cur) {
            long next = cur;
            for (Room room : rooms) {
                if (room.cur == cur) {
                    next = getNext(rooms, room.next);
                }
            }
            return next;
        }
        public static class Room{
            long cur;
            long next;
            public Room(long a, long b){
                this.cur = a;
                this.next = b;
            }
        }
    }
}

