package SeongJun.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RGB거리_1149 {
    public static void main(String[] args) throws IOException {

        //입력받는부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //집 배열 만들기
        int[][] house = new int[N][3];

        //집 색깔을 넣어주기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            house[i][0]= Integer.parseInt(st.nextToken());
            house[i][1]= Integer.parseInt(st.nextToken());
            house[i][2]= Integer.parseInt(st.nextToken());
        }

//무작정 최솟값으로만 구하면 안되고 모든 경로의 경우의 수를 찾아서 마지막때 RGB 중에 가장 작은걸 구해야 한다.
        // i-1 번째의 최솟값을 구하기
        for (int i = 1; i < N; i++) {
            house[i][0] += Math.min(house[i-1][1],house[i-1][2]);
            house[i][1] += Math.min(house[i-1][0],house[i-1][2]);
            house[i][2] += Math.min(house[i-1][0],house[i-1][1]);


        }
        // 마지막에 최솟값을 출력해주기
        System.out.println(Math.min(Math.min(house[N-1][0],house[N-1][1]),house[N-1][2]));

    }




}

