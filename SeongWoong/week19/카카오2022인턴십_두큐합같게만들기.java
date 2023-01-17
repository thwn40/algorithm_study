import java.util.*;

public class 카카오2022인턴십_두큐합같게만들기 {
    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            int cnt = 0;

            long sum1 = getSum(queue1);
            long sum2 = getSum(queue2);

            // 내가 생각한 탈출 조건 maxSize
            // 3,3,3,3 and 3,3,21,3
            int maxSize = 0;
            if (sum1>sum2){
                maxSize = queue1.length*2-3 + queue2.length;
            } else {
                maxSize = queue2.length*2-3 + queue1.length;
            }

            Queue<Integer> q1 = arrayToQueue(queue1);
            Queue<Integer> q2 = arrayToQueue(queue2);

            while(sum1!=sum2){
                if (sum1>sum2){
                    int temp = q1.poll();
                    q2.add(temp);
                    sum1 -= temp;
                    sum2 += temp;
                    cnt++;
                }else {
                    int temp = q2.poll();
                    q1.add(temp);
                    sum2 -= temp;
                    sum1 += temp;
                    cnt++;
                }
                if (cnt>maxSize) return -1;
            }

            return cnt;
        }

        public int getSum(int[] arr){
            int sum = 0;
            for(int i=0;i<arr.length;i++){
                sum += arr[i];
            }
            return sum;
        }

        public Queue arrayToQueue(int[] arr){
            Queue<Integer> q = new LinkedList<>();
            for(int i=0;i<arr.length;i++){
                q.add(arr[i]);
            }
            return q;
        }
    }
}
