package level18_그리디알고리즘_Clear.Q2_1931;

/*
문제) 회의실 배정
    한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
    각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
    단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
    회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.

입력)
    첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다.
    둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
    시작 시간과 끝나는 시간은 2^31-1보다 작거나 같은 자연수 또는 0이다.

출력)
    첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    /*
    Sudo Code)
        1. 입력 값을 받는다
            (
                첫째줄[N] - int : 회의 수
                N째줄[meetingTokenizer] - StringTokenizer : 회의 시작시간, 종료시간
            )

        2. Meeting 클래스를 구현(Comparable 상속)
            - compareTo 메서드를 오버라이딩하여 endTime 기준으로 오름차순 정렬
            - endTime이 같을 경우 startTime이 빠른 순으로 정렬

        3. ArrayList를 생성해 입력 값으로 반복문을 돌려 startTime과 endTime을 저장

        4. Collections.sort() 메서드를 통해 Meeting 클래스에 오버라이딩된 compareTo의 코드 기준으로 정렬

        5. 정렬된 리스트를 통해 최대 미팅 횟수를 구할 greedy 메서드 구현

        6. greedy
            - input : ArrayList<Meeting>
            - 향상된 for문(Enhanced for)을 이용해 meet(Meet 객체)을 리스트에서 가져와 비교
            - if(endTime <= meeting.startTime)인 경우(미팅이 끝나는 시간과 미팅이 시작하는 시간이 안겹치는 경우) :
                 startTime = meet.startTime을 대입해준다.
                 endTime = meet.endTime을 대입해준다.
                 cnt를 증감연산해준다.
            - output : cnt(총 미팅 수)

        7. cnt(총 미팅 수) 출력
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Meeting> meetingList = new ArrayList<>();
        String [] str = {};


        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            meetingList.add(new Meeting(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }


        Collections.sort(meetingList);

        for (Meeting me:meetingList) {
            System.out.println("start = " + me.startTime+", end = "+me.endTime);
        }

        int ans = greedy(meetingList);
        System.out.println(ans);
    }

    public static int greedy(ArrayList<Meeting> list) {
        int cnt = 0;
        int startTime = 0;
        int endTime = 0;

        for (Meeting meeting: list) {
            if(endTime <= meeting.startTime) {
                startTime = meeting.startTime;
                endTime = meeting.endTime;
                cnt++;
            }
        }
        return cnt;
    }
}

class Meeting implements Comparable<Meeting>{
    int startTime = 0;
    int endTime = 0;

    Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public int compareTo(Meeting o) {
        if (this.endTime == o.endTime) return this.startTime - o.startTime;
        return this.endTime - o.endTime;
    }
}

