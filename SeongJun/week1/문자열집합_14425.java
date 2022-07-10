package BackJoonAlgorithm.AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 문자열집합_14425 {
    /**
     * v1.
     * 1. N개의 문자열을 입력받아서 numberOfStrings 배열에 넣는다
     * 2. 중복을 제거하기위해 M개의 받은 문자열을 HashSet에 넣어준다.
     * 3. M개의 문자열 중에 총 몇개가 집합 S에 포함되어있는지 출력한다.
     *
     * v2 - 리팩토링 - BufferReader 사용
     * v3 - 이분검색버젼
     */


    static void BJ14425_V1() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int answer = 0;

        HashMap<String, Integer> numberOfStrings = new HashMap();

        for (int i = 0; i <= N; i++) {
            String key = scanner.nextLine();
            numberOfStrings.put(key, 0);
        }

        for (int i = 0; i < M; i++) {
            String key = scanner.nextLine();
            if (numberOfStrings.containsKey(key))
                answer++;
        }

        System.out.println(answer);
    }

    static void BJ14425_V2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;

        HashMap<String, Integer> numberOfStrings = new HashMap();

        for (int i = 0; i < N; i++) {
            numberOfStrings.put(br.readLine(), 0);
        }

        for (int i = 0; i < M; i++) {
            if (numberOfStrings.containsKey(br.readLine()))
                answer++;
        }

        System.out.println(answer);
    }

    static void BJ14425_V3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;

        String[] numberOfStringsArr = new String[N];


        for (int i = 0; i < N; i++) {
            numberOfStringsArr[i] = br.readLine();
        }

        Arrays.sort(numberOfStringsArr);
        for (int i = 0; i < M; i++) {
            if (Arrays.binarySearch(numberOfStringsArr, br.readLine()) > 0)
                answer++;
        }

        System.out.println(answer);
    }


    public static void main(String[] args) throws IOException {
        BJ14425_V1();
        BJ14425_V2();
        BJ14425_V3();
    }
}





