package SeongJun.week23;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class 찾기 {
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
				s[i]=j+1;
				j+=1;
				// s[i] = ++j;
			}
			//끝까지 밀었는데 없으면 실패함수에 0 넣기
			else{
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

		String s = br.readLine();
		String p = br.readLine();
		int j = 0;
		int[] fail = failFunction(p);
		int result = 0;
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != p.charAt(j)) {
				j = fail[j - 1];
			}
			if (s.charAt(i) == p.charAt(j)) {
				if(j==p.length()-1){
					arr.add(i-p.length()+2);
					result++;
					j=fail[j];
				}
				else{
					j++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		arr.forEach(str -> {
			sb.append(str).append(" ");
		});
		System.out.println(sb);
		br.close();
	}
}

