package SeongJun.week22;

import java.util.Scanner;

public class 가장긴바이토닉부분수열 {


		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int T = sc.nextInt();
			int[] arr = new int[T];
			for (int i = 0; i < T; i++) {
				arr[i]= sc.nextInt();
			}


			int[] dp1 = new int[T+1];

			for (int i = 0; i < T; i++) {
				dp1[i]=1;
				for (int j = 0; j < i; j++) {

					if(arr[j]<arr[i]&&dp1[i]<dp1[j]+1){
						dp1[i]=dp1[j]+1;
					}

				}

			}

			int[] dp2 = new int[T + 1];
			for (int i = T-1; i >= 0; i--) {
				dp2[i] = 1;

				for (int j = T-1; j > i; j--) {
					if (arr[j] > arr[i] && dp2[i] < dp2[j] + 1) {
						dp2[i] = dp2[j] + 1;
					}

				}

			}

			int[] d2 = new int[T];
			for (int i=T-1; i>=0; i--) {
				d2[i] = 1;
				for (int j=i+1; j<T; j++) {
					if (arr[i] > arr[j] && d2[j]+1 > d2[i]) {
						d2[i] = d2[j]+1;
					}
				}
			}

			int ans = dp1[0]+d2[0]-1;
			for (int i=0; i<T; i++) {
				int i1 = dp1[i] + d2[i] - 1;
				if (ans < i1) {
					ans = i1;
				}
			}

			System.out.println(ans);
		}
	}

