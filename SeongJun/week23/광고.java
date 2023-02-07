package SeongJun.week23;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 광고 {
	static int[] failFunction(String p) {
		int m = p.length();
		int[] s = new int[m];
		s[0] = 0;
		int j = 0;
		for (int i = 1; i < m; i++) {
			//둘이 같지 않으면 한칸씩 뒤로 가면서 j에 실패함수 값을 넣음
			while (j > 0 && p.charAt(i) != p.charAt(j)) {
				j = s[j - 1];
			}
			//둘이 같으면 i와 j 모두 하나씩 증가
			if (p.charAt(i) == p.charAt(j)) {
				s[i] = j + 1;
				j += 1;
				// s[i] = ++j;
			}
			//끝까지 밀었는데 없으면 실패함수에 0 넣기
			else {
				s[i] = 0;
			}
		}
		return s;
	}

	static void kmp() {

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int[] ints = failFunction(s);
		// System.out.println(Arrays.toString(ints));
		System.out.println(n-ints[n-1]);
		br.close();
	}
}
