package SeongJun.week9;

import java.util.ArrayList;
import java.util.Scanner;


public class 전화번호목록_5052 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie trie;
        ArrayList<String> phoneNums;
        boolean answer;
        String phoneNumber;

        //테스트케이스 갯수
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            //트라이 생성후 최상단에 루트노드 넣기
            trie = new Trie(new Node(0));
            phoneNums = new ArrayList<>();
            answer = true;

            //전화번호의 수
            int n = sc.nextInt();
            sc.nextLine();

            //트라이에 전화번호부 입력
            for (int j = 0; j < n; j++) {
                phoneNumber = sc.nextLine();
                phoneNums.add(phoneNumber);
                trie.insert(phoneNumber);
            }

            //정답
            for (String phoneNum : phoneNums) {
                if (!trie.search(phoneNum)) {
                    answer = false;
                    System.out.println("NO");
                    break;
                }
            }
            if (answer) System.out.println("YES");
        }


    }


    // 트라이
    static class Trie {
        Node root;

        Trie(Node root) {
            this.root = root;
        }

        //삽입 -> 전화번호부에 넣기
        public void insert(String phoneNum) {
            //1. 일단 처음 부모노드는 루트노드로 설정
            Node currNode = this.root;
            int num;
            //2.전화번호의 각 번호를 순회
            for (char c : phoneNum.toCharArray()) {
                num = Character.getNumericValue(c);
                //3.현재노드가 이 문자(num)을 자식으로 갖고있는지 검사
                //4.없으면 자식 노드를 생성하고 현재노드를 새 노드로 교체하고 2번으로
                if (currNode.children[num] == null) {
                    Node node = new Node(num);
                    currNode.addNode(node);
                    currNode = node;
                }
                //5. 있으면 현재노드를 그 자식노드로 업데이트하고 2번으로
                else {
                    currNode = currNode.children[num];
                }
            }
            //6. 삽입이 끝났으면 *추가
            currNode.addNode(new Node(-1));
        }

        //탐색 -> 전화걸기
        public boolean search(String phoneNum) {
            //1. 일단 처음 부모노드는 루트노드로 설정
            Node currNode = this.root;
            //2. 전화번호 누르는중
            for (char c : phoneNum.toCharArray()) {
                int num = Character.getNumericValue(c);
                //3.현재노드가 이 문자(num)을 자식으로 갖고있는지 검사
                //4.이문자를 자식으로 가지고 있으면 계속 탐색
                if (currNode.children[num] != null && currNode.children[10] == null) {
                    currNode = currNode.children[num];
                }
                //5. 10번방 만나면 전화걸린거니까 종료
                else {
                    return false;
                }

            }
            return true;
        }


    }


    static class Node {
        int num;
        Node[] children = new Node[11];

        public Node(int num) {
            this.num = num;
        }

        //-1이 들어오면 10번 방에 넣는다.
        public void addNode(Node node) {
            if (node.num == -1) {
                children[10] = node;
            } else {
                children[node.num] = node;
            }

        }

        @Override
        public String toString() {
            return "{" + "num=" + num + '}';
        }

    }
}
