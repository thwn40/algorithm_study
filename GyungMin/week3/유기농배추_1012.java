package Gyungmin.week3;

/*
문제) 유기농 배추
    차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다.
    농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에,
    한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다.
    이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다.
    특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어,
    그 배추들 역시 해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.

    한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다.
    배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로
    서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
    예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다.
    0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.

    1	1	0	0	0	0	0	0	0	0
    0	1	0	0	0	0	0	0	0	0
    0	0	0	0	1	0	0	0	0	0
    0	0	0	0	1	0	0	0	0	0
    0	0	1	1	0	0	0	1	1	1
    0	0	0	0	1	0	0	1	1	1

입력)
    입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
    그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50),
    그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다.
    그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.

출력)
    각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.

*/

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추_1012 {
    static int col;
    static int row;
    static int[][] cabbageFarm;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visitedFarmArea;
    static int minimumNumOfWhiteWarm = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numOfTestCase = Integer.parseInt(br.readLine());
        int numOfCabbage = 0;
        StringTokenizer MNK;
        StringTokenizer inputXy;
        int x, y;

        for (int testCase = 0; testCase < numOfTestCase; testCase++) {

            // M : 가로길이
            // N : 세로길이
            // K : 배추가 심어져있는 위치 갯수
            MNK = new StringTokenizer(br.readLine());
            col = Integer.parseInt(MNK.nextToken());
            row = Integer.parseInt(MNK.nextToken());
            numOfCabbage = Integer.parseInt(MNK.nextToken());

            cabbageFarm = new int[row][col];
            visitedFarmArea = new boolean[row][col];

            // 입력받을 xy 쌍 배열에 기입(1)
            for (int i = 0; i < numOfCabbage; i++) {
                inputXy = new StringTokenizer(br.readLine());
                x = Integer.parseInt(inputXy.nextToken());
                y = Integer.parseInt(inputXy.nextToken());
                cabbageFarm[y][x] = 1;
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (cabbageFarm[i][j] != 0 && !visitedFarmArea[i][j]) {
                        bfsCabbageFarm(i, j);
                        minimumNumOfWhiteWarm++;
                    }
                }
            }
            bw.write(minimumNumOfWhiteWarm + "\n");
            minimumNumOfWhiteWarm = 0;
        }

        br.close();
        bw.close();
    }

    public static void bfsCabbageFarm(int y, int x) {
        Queue<int[]> cabbageQue = new LinkedList<>();
        cabbageQue.offer(new int[]{y, x});
        visitedFarmArea[y][x] = true;

        while (!cabbageQue.isEmpty()) {
            int[] que = cabbageQue.poll();
            int cy = que[0];
            int cx = que[1];

            for (int i = 0; i < 4; i++) {
                int CurY = cy + dy[i];
                int CurX = cx + dx[i];
                if (CurY < 0 | CurX < 0 | row <= CurY | col <= CurX) continue;
                if (cabbageFarm[CurY][CurX] != 1 || visitedFarmArea[CurY][CurX]) continue;
                if (cabbageFarm[CurY][CurX] == 1 && !visitedFarmArea[CurY][CurX]) {
                    cabbageQue.offer(new int[]{CurY, CurX});
                    visitedFarmArea[CurY][CurX] = true;
                }

            }
        }
    }
}

