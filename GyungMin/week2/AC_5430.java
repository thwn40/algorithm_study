package GyungMin.week2;

/*
문제) AC
    선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다.
    AC는 정수 배열에 연산을 하기 위해 만든 언어이다.
    이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
    함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다.
    배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
    함수는 조합해서 한 번에 사용할 수 있다.
    예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다.
    예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
    배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.

입력)
    첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
    각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다.
    p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
    다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
    다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
    전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.

출력)
    각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다.
    만약, 에러가 발생한 경우에는 error를 출력한다.

*/

import java.io.*;
import java.util.LinkedList;

public class AC_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // testN : 총 테스트케이스 수
        int testN = Integer.valueOf(br.readLine());
        char[] func;
        int numOfArray;
        String str;
        String[] tmp;
        LinkedList<Integer> dequeArray;
        boolean err;
        boolean isRight;


        /*
            예시)             배열 :  1, 2, 3, 4
                      리버스옵션(R) :  4, 3, 2, 1
                      -> 따라서 왼쪽에서 시작할 것인지, 오른쪽에서 시작할 것인지만 파악하면 됨, 굳이 덱 넣고 빼고 안해도 된다.

                      델리트옵션(D) :     3, 2, 1
               최종적으로 만들어야될 것 :  리스트 or error
        */
        for (int i = 0; i < testN; i++) {
            /*
                dequeArray : 덱은 링크드리스트로 테스트케이스가 끝나면 계속 초기화
                err        : 에러가 발생했는지 여부, 테스트케이스가 끝나면 계속 초기화
                isRight    : 리스트 시작 방향(왼쪽, 오른쪽) 테스트케이스가 끝나면 계속 초기화 (false : 왼쪽)
                func       : 옵션 값을 저장한 변수(타입은 Character 배열)
                numOfArray : 배열 원소의 개수
                str        : 입력받은 리스트 모양 문자열에 substring 메서드를 사용하기 위한 임시 변수
                tmp        : substring 메서드와 split 메서드를 사용해 만든 String 배열
            */

            dequeArray = new LinkedList<>();
            err = false;
            isRight = false;
            func = br.readLine().toCharArray();
            numOfArray = Integer.valueOf(br.readLine());
            str = br.readLine();
            tmp = str.substring(1, str.length() - 1).split(",");

            // forEach(Enhanced For)문을 사용해 String 배열의 값이 있는지 확인하고 덱에 값을 넣어준다.
            for (String s: tmp) {
                if (!s.equals("")) dequeArray.add(Integer.valueOf(s));
            }

            // 총 옵션의 길이만큼
            for (int j = 0; j < func.length; j++) {
                // 델리트 옵션일 경우
                if (func[j]=='D') {
                    // 덱의 크기가 0이라면 에러를 버퍼에 쌓아주고 break 문(중복 에러 방지)을 통해 반복문을 탈출해준다.
                    // 리스트 후처리를 위한 err, err 발생 시 값이 true 가 된다. -> true 일 경우 리스트 후처리 x
                    if (dequeArray.size() == 0) {
                        bw.write("error\n");
                        err = true;
                        break;
                    }
                    // 델리트 옵션에서 리스트가 0이 아닌 경우,
                    // isRight 가 참인지 아닌지를 보고 방향에 따라 remove를 해준다.
                    if (isRight) dequeArray.removeLast();
                    else dequeArray.removeFirst();

                    // 리버스 옵션인 경우 isRight 값을 뒤집어준다.
                } else isRight = !isRight;
            }

            // 위에 반복문에서 에러가 발생하지 않았을 경우,
            // 반복문을 무사히 마친 후에 대한 후처리(리스트가 살아남은 경우)
            if (!err) {
                bw.write("[");
                // 덱의 사이즈 0이 아닐 때까지 리스트 원소의 버퍼를 계속 쌓아준다.
                while (dequeArray.size()!=0) {
                    // 조건연산자 사용 isRight = true 일 때 오른쪽부터 리스트를 만들어준다. 아닐 경우 반대.
                    // 그리고 remove 후 덱의 크기가 0이 아니라면(덱의 제일 마지막이 아닌 경우) 원소 구분 콤마를 붙여준다.
                    bw.write(isRight ? String.valueOf(dequeArray.removeLast()) : String.valueOf(dequeArray.removeFirst()));
                    if (dequeArray.size()!=0) bw.write(",");
                }
                bw.write("]\n");
            }

        }
        // 메모리 제거
        br.close();
        bw.close();
    }
}
