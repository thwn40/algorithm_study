package SeongWoo.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boggle {

    public static class Node {
        int row;
        int col;
        boolean check = false;
        char character;
        String word;
        TrieNode trieNode;

        public Node(int row, int col, char character) {
            this.row = row;
            this.col = col;
            this.character = character;
        }
    }


    public static class TrieNode {

        boolean end = false;
        HashMap<Character, TrieNode> childNodes = new HashMap<>();
        int[] dRow = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dCol = {0, 1, 1, 1, 0, -1, -1, -1};

        public void add(String word) {
            TrieNode trieNode = this;

            for (int i = 0; i < word.length(); i++) {
                trieNode = trieNode.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }

            trieNode.end = true;
        }

        public void search(Node[][] board, Node node, List<String> wordList) {

            node.check = true;

            if (node.trieNode.end) {
                wordList.add(node.word);
            }

            for (int i = 0; i < 8; i++) {
                int nextRow = node.row + dRow[i];
                int nextCol = node.col + dCol[i];
                TrieNode trieNode = node.trieNode;

                if (nextRow < 0 || nextRow >= board.length || nextCol < 0 || nextCol >= board[0].length
                        || trieNode.childNodes.get(board[nextRow][nextCol].character) == null
                        || node.word.length() == 8
                        || board[nextRow][nextCol].check) {
                    continue;
                }

                Node nextNode = board[nextRow][nextCol];
                nextNode.trieNode = trieNode.childNodes.get(nextNode.character);
                nextNode.word = new StringBuffer(node.word)
                        .append(nextNode.character)
                        .toString();

                search(board, nextNode, wordList);
            }
            node.check = false;
            node.word = null;
            node.trieNode = null;

        }
    }

    private static void printResult(List<String> wordList, int[] scoreArr) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        String maxString = "";
        int score = 0;

        for (String word : wordSet) {
            score += scoreArr[word.length()];
            if (maxString.length() < word.length()) {
                maxString = word;
            } else if (maxString.length() == word.length() && maxString.compareTo(word) > 0) {
                maxString = word;
            }
        }

        System.out.println(score +  " " + maxString + " " + wordSet.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        TrieNode trieNode = new TrieNode();
        Node[][] board = new Node[4][4];
        int[] scoreArr = {0, 0, 0, 1, 1, 2, 3, 5, 11};

        for (int i = 0; i < w; i++) {
            String word = new StringTokenizer(br.readLine()).nextToken();
            trieNode.add(word);
        }

        new StringTokenizer(br.readLine());
        st = new StringTokenizer(br.readLine());
        int caseSize = Integer.parseInt(st.nextToken());

        while (caseSize-- > 0) {
            List<String> wordList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                String word = new StringTokenizer(br.readLine()).nextToken();
                for (int j = 0; j < 4; j++) {
                    board[i][j] = new Node(i, j, word.charAt(j));
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (trieNode.childNodes.get(board[i][j].character) == null) {
                        continue;
                    }
                    board[i][j].trieNode = trieNode.childNodes.get(board[i][j].character);
                    board[i][j].word = String.valueOf(board[i][j].character);
                    trieNode.search(board, board[i][j], wordList);
                }
            }

            Collections.sort(wordList);
            printResult(wordList, scoreArr);

            if (caseSize > 0) {
                new StringTokenizer(br.readLine());
            }
        }
    }
}
