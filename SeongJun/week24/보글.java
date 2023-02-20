package SeongJun.week24;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class 보글 {
	static int[] dr;
	static int[] dc;
	static HashSet<String> answer;
	static boolean[][] visited;
	static Trie trie = new Trie();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int w = Integer.parseInt(br.readLine());
		dr = new int[]  { 0, 0, -1, 1, 1, 1, -1, -1 };
		dc = new int[]  { -1, 1, 0, 0, 1, -1, 1, -1 };
		for (int i = 0; i < w; i++) {
			String str = br.readLine();
			trie.insert(str);
		}

		br.readLine();

		int b = Integer.parseInt(br.readLine());

		for (int i = 0; i < b; i++) {
			char[][] board = new char[4][4];
			answer = new HashSet();
			visited = new boolean[4][4];

			for (int c = 0; c < 4; c++) {
				String s = br.readLine();
				for (int r = 0; r < 4; r++) {
					board[c][r] = s.charAt(r);
				}
			}

			if (i < b - 1) {
				br.readLine();
			}

			for (int c = 0; c < 4; c++) {
				for (int r = 0; r < 4; r++) {
					visited[c][r] = true;
					dfs(board, c,r, new StringBuilder(board[c][r]+""));
					visited[c][r] = false;
				}
			}
			int score = 0;
			String longest = "";
			int cnt = 0;

			Iterator<String> it = answer.iterator();

			while (it.hasNext()) {
				String word = it.next();

				score += getScore(word);

				if (longest.length() < word.length()
					|| (longest.length() == word.length() && word.compareTo(longest) < 0))
					longest = word;
			}

			cnt = answer.size();
			bw.write(score + " " + longest + " " + cnt + "\n");

		}
		bw.flush();
	}

	public static void dfs(char[][] board, int c, int r, StringBuilder sb) {

		visited[c][r] = true;
		if (trie.contains(sb.toString()) == -1) {
			return;
		}

		if (trie.contains(sb.toString()) == 1) {
			answer.add(sb.toString());
		}

		for (int i = 0; i < 8; i++) {

			int nextRow = r+ dr[i];
			int nextCol = c + dc[i];

			if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) {
				continue;
			}

			if (visited[nextCol][nextRow]) {
				continue;
			}

			visited[nextCol][nextRow] = true;
			sb.append(board[nextCol][nextRow]);
			dfs(board, nextCol,nextRow, sb);
			visited[nextCol][nextRow] = false;
			sb.deleteCharAt(sb.length() - 1);
		}
	}


	private static int getScore(String s) {
		switch (s.length()) {
			case 3:
			case 4:
				return 1;
			case 5:
				return 2;
			case 6:
				return 3;
			case 7:
				return 5;
			case 8:
				return 11;
			default:
				return 0;

		}
	}

	static class Trie {
		private TrieNode rootNode;

		public Trie() {
			this.rootNode = new TrieNode();
		}

		public void insert(String string) {
			TrieNode thisNode = rootNode;
			for (int j = 0; j < string.length(); j++) {
				char ch = string.charAt(j);
				thisNode = thisNode.getChildNodes().computeIfAbsent(ch, key -> new TrieNode());
			}

			thisNode.setIsLastChar(true);

		}

		public int contains(String word) {

			TrieNode thisNode = rootNode;

			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);

				thisNode = thisNode.getChildNodes().get(ch);
				if (thisNode == null)
					return -1;
			}

			return thisNode.isLastChar() ? 1 : 0;
		}


	}

	static class TrieNode {
		private HashMap<Character, TrieNode> childNodes;
		private boolean isLastChar;

		TrieNode() {
			childNodes = new HashMap<>();
		}

		public HashMap<Character, TrieNode> getChildNodes() {
			return childNodes;
		}

		public void setIsLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}

		public boolean isLastChar() {

			return isLastChar;
		}
	}
}



