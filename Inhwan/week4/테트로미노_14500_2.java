package Inhwan.week4;

import java.io.*;
import java.util.*;

public class 테트로미노_14500_2 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nm[0], m = nm[1];
        int[][] board = new int[n][];
        for (int i = 0; i < n; i++) board[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][][][] Tets ={
                { {{0,0},{0,1},{1,0},{1,1}},
                },
                { {{0,0},{0,1},{0,2},{0,3}}
                },
                { {{0,0},{1,0},{2,0},{3,0}}
                },
                { {{0,0},{0,1},{0,2},{1,0}},
                  {{0,0},{0,1},{0,2},{1,1}},
                  {{0,0},{0,1},{0,2},{1,2}},
                  {{1,0},{1,1},{1,2},{0,0}},
                  {{1,0},{1,1},{1,2},{0,1}},
                  {{1,0},{1,1},{1,2},{0,2}},
                  {{0,0},{0,1},{1,1},{1,2}},
                  {{1,0},{1,1},{0,1},{0,2}}
                },
                { {{0,0},{1,0},{2,0},{0,1}},
                  {{0,0},{1,0},{2,0},{1,1}},
                  {{0,0},{1,0},{2,0},{2,1}},
                  {{0,1},{1,1},{2,1},{0,0}},
                  {{0,1},{1,1},{2,1},{1,0}},
                  {{0,1},{1,1},{2,1},{2,0}},
                  {{0,0},{1,0},{1,1},{2,1}},
                  {{0,1},{1,1},{1,0},{2,0}}
                }
        };
        int[][] size = {{1,1},{0,3},{3,0},{1,2},{2,1}};

        int M = 0, sum;

        for (int i = 0; i<5; i++){
            for (int[][] tets : Tets[i]) {
                for (int j = 0; j < n-size[i][0]; j++) {
                    for (int k = 0; k < m-size[i][1]; k++) {
                        sum=0;
                        for (int[] t : tets) {
                            sum+=board[t[0]+j][t[1]+k];
                        }
                        M = Math.max(M,sum);
                    }
                }
            }
        }
        System.out.println(M);
    }
}
