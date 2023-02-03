package SeongWoo.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 접두사_찾기_14426 {

    public static class TrieNode {

        boolean end = false;
        HashMap<Character, TrieNode> childNodes = new HashMap<>();

        public TrieNode() {}

        public void add(String word) {
            TrieNode trieNode = this;

            for (int i = 0; i < word.length(); i++) {
                trieNode = trieNode.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }

            trieNode.end = true;
        }

        public boolean search(String word) {
            TrieNode currentNode = this;

            for (int i = 0; i < word.length(); i++) {
                if (currentNode.end) {
                    return false;
                }

                currentNode = currentNode.childNodes.get(word.charAt(i));

                if (currentNode == null) {
                    return false;
                }
            }
            return true;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int count = 0;

        TrieNode trieNode = new TrieNode();

        for (int i = 0; i < n; i++) {
            String word = new StringTokenizer(br.readLine()).nextToken();
            trieNode.add(word);
        }

        for (int i = 0; i < m; i++) {
            String searchedWord = new StringTokenizer(br.readLine()).nextToken();
            if (trieNode.search(searchedWord)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
