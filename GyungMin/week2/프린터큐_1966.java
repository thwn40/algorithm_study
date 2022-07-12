package GyungMin.week2;

/*
문제) 프린터 큐
    여러분도 알다시피 여러분의 프린터 기기는 여러분이 인쇄하고자 하는 문서를 인쇄 명령을 받은
    ‘순서대로’, 즉 먼저 요청된 것을 먼저 인쇄한다.
    여러 개의 문서가 쌓인다면 Queue 자료구조에 쌓여서 FIFO - First In First Out - 에 따라 인쇄가 되게 된다.
    하지만 상근이는 새로운 프린터기 내부 소프트웨어를 개발하였는데,
    이 프린터기는 다음과 같은 조건에 따라 인쇄를 하게 된다.

    현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
    나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면,
    이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다.
    그렇지 않다면 바로 인쇄를 한다.
    예를 들어 Queue에 4개의 문서(A B C D)가 있고, 중요도가 2 1 4 3 라면 C를 인쇄하고,
    다음으로 D를 인쇄하고 A, B를 인쇄하게 된다.

    여러분이 할 일은 현재 Queue에 있는 문서의 수와 중요도가 주어졌을 때,
    어떤 한 문서가 몇 번째로 인쇄되는지 알아내는 것이다.
    예를 들어 위의 예에서 C문서는 1번째로, A문서는 3번째로 인쇄되게 된다.

입력)
    첫 줄에 테스트케이스의 수가 주어진다.
    각 테스트케이스는 두 줄로 이루어져 있다.
    테스트케이스의 첫 번째 줄에는 문서의 개수 N(1 ≤ N ≤ 100)과,
    몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수 M(0 ≤ M < N)이 주어진다.
    이때 맨 왼쪽은 0번째라고 하자.
    두 번째 줄에는 N개 문서의 중요도가 차례대로 주어진다.
    중요도는 1 이상 9 이하의 정수이고, 중요도가 같은 문서가 여러 개 있을 수도 있다.

출력)
    각 테스트 케이스에 대해 문서가 몇 번째로 인쇄되는지 출력한다.

*/

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class 프린터큐_1966 {
    public static void main(String[] args) throws IOException {

        // 입력 값을 받기위한 BufferedReader 와 출력 값을 쌓기위한 BufferedWriter
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 총 테스트 케이스 횟수
        int N = Integer.parseInt(br.readLine());

        // 자바 큐 링크드리스트 선언, QueueToken 객체는 인덱스와 우선순위 값을 저장한다.
        Queue<QueueToken> printQueue = new LinkedList<>();

        /*
                        st : 출력 개수와 관심있는 인덱스 값이 입력으로 들어오는 String 을 분리해서 저장하기 위한 StringTokenizer
                numOfPrint : 출력물의 개수
                     order : 관심있는 출력물의 인덱스
           totalNumOfPrint : 출력횟수
                        qt : QueueToken 객체, 초기화는 반복문에서..
                       tmp : split 메서드를 사용하기 위한 임시 String 배열
                  priority : 우선순위를 정렬하기 위한 배열, Arrays.sort() 메서드를 사용해 우선순위를 정렬해준다.
        */
        StringTokenizer st;
        int order = 0;
        int numOfPrint ;
        int totalNumOfPrint;
        QueueToken qt;
        String [] tmp;
        int [] priority = {};


        // 총 테스트 케이스 만큼 반복문
        for (int i = 0; i < N; i++) {
            // totalNumOfPrint : 출력횟수, 테스트 케이스마다 초기화
            totalNumOfPrint = 0;

            // numOfPrint(출력물 개수), order(관심있는 출력물의 인덱스)
            st = new StringTokenizer(br.readLine());
            numOfPrint = Integer.parseInt(st.nextToken());
            order = Integer.parseInt(st.nextToken());

            // queue에 담기 전에 먼저 split 메서드를 이용해 String 배열에 담아준다.
            tmp = br.readLine().split(" ");
            if (numOfPrint == 1) {
                bw.write(1 + "\n");
                continue;
            }

            // tmp 배열과 Stream 클래스를 활용해 우선순위에 대한 int형 배열을 만들어준 뒤, Arrays.sort()메서드를 사용해 priority 배열을 정렬해준다. (default : 오름차순 정렬)
            priority = Stream.of(tmp).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(priority);

            // 인덱스와 우선순위를 가지고 QueueToken 객체를 생성한 뒤, printQueue 에 담아준다.
            for (int j = 0; j < tmp.length; j++) {
                printQueue.add(new QueueToken(j, tmp[j].charAt(0) - '0'));
            }

            // printQueue 가 빌때까지
            while (!printQueue.isEmpty()) {

                // printQueue 의 제일 앞에서 값을 하나 가져와 qt 라는 객체를 해당 값으로 초기화해준다.
                qt = printQueue.poll();

                // priority 배열이 오름차순 정렬이기 때문에 배열의 가장 마지막 부분(가장 큰 값)부터 qt.prior(우선순위)와 비교
                // 출력이 되서 카운트가 되면 우선순위의 변경이 일어나므로 우선순위 배열의 인덱스에 totalNumOfPrint(총 출력 횟수)를 빼줘야 한다.
                if (priority[priority.length - 1 - totalNumOfPrint] == qt.prior) {

                    // 조건문을 만족하면 일단 출력물은 +1이 된다.
                    totalNumOfPrint ++;

                    // 여기서 qt.idx == order : 즉, 관심있는 출력물 인덱스와 큐 객체의 인덱스 값이 같을 경우 원하는 관심 객체를 찾아 조건문을 만족한다.
                    if (qt.idx == order) {

                        // BufferedWriter 객체에 출력된 출력물 수와 줄바꿈 문자열을 쌓아주고 break 문을 걸어 반복문(while 문)을 종료해준다.
                        bw.write(totalNumOfPrint + "\n");
                        break;
                    }
                } else {
                    // 우선순위가 맞는 것을 못찾았다면 앞에서 하나 가져왔던 것을 도로 뒤에 붙여준다.
                    printQueue.add(qt);
                }

            }
            // 문자열 버퍼를 쌓아 while 문을 탈출했다면 Queue를 초기화해준다.
            printQueue.clear();
        }

        // 모든 반복문에서 빠져나왔다면 입, 출력 객체의 메모리를 제거해준다.
        br.close();
        bw.close();
    }

    // 출력물의 인덱스와 우선순위를 저장하기 위한 클래스
    public static class QueueToken{
        int idx;
        int prior;

        public QueueToken(int idx, int prior) {
            this.idx = idx;
            this.prior = prior;
        }

    }
}
