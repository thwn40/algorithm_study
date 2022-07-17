package SeongJun.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AC_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n;
        ArrayList<String> answers = new ArrayList<>();

        Loop1 :
        for (int i = 0; i < T; i++) {
            Deque<Integer> deque = new LinkedList<>();
            char[] p = br.readLine().toCharArray();
            n = Integer.parseInt(br.readLine());
            StringBuffer arr = new StringBuffer(br.readLine());
            arr.deleteCharAt(0);
            arr.deleteCharAt(arr.length()-1);
            StringTokenizer st = new StringTokenizer(arr.toString(), ",");
            for (int j = 0; j < n; j++) {
                deque.offer(Integer.valueOf(st.nextToken()));
            }

            int totalR = 0;
            boolean trueStackFalseQueue = false;
            Loop2 :
            for (char c : p) {


                if (c=='R') {
                    totalR++;
                    trueStackFalseQueue = !trueStackFalseQueue;
                }
                if (c=='D'&&!deque.isEmpty()) {
                    if (trueStackFalseQueue) {
                        deque.pollLast();

                    } else {
                        deque.pollFirst();

                    }
                }
                else if(c=='D'&&deque.isEmpty()){
                    System.out.println("error");
                    continue Loop1;

                }


            }
            if(totalR%2==1){
                for (int j = 0; j < deque.size()-1; j++) {
                    deque.offerLast(deque.pollFirst());

                }
                System.out.println(deque.toString().replace(" ", ""));

            }
            else{
                System.out.println(deque.toString().replace(" ", ""));

            }


        }

    }

}
