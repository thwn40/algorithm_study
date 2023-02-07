package SeongJun.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경로찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int[][] ans = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				ans[i][j] = num;
			}
		}



		for (int i = 0; i < N; i++) {
			//i번 정점
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					//j부터 i까지 이어져있고 i부터 k까지 이어져있으면 j부터 k는 이어져있는것
					if(ans[j][i]==1&&ans[i][k]==1){
						ans[j][k]=1;
					}
				}
			}
		}
		for (int i = 0; i < ans.length; i++) {
			for (int i1 = 0; i1 < ans.length; i1++) {
				System.out.print(ans[i][i1]+" ");
			}
			System.out.println();
		}
	}
}
