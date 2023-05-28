import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 트리순회_1991 {
    static Node parent = new Node('A', null, null);
    static StringBuffer[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            char root = str.charAt(0);
            char left = str.charAt(2);
            char right = str.charAt(4);
            insert(parent,root,left,right);
        }
        ans = new StringBuffer[3];
        for (int i = 0; i < 3; i++) {
            ans[i] = new StringBuffer();
        }
        preOrder(parent);
        inOrder(parent);
        postOrder(parent);
        for (int i = 0; i < 3; i++) {
            System.out.println(ans[i]);
        }
    }

    public static void insert(Node n, char root, char left, char right) {
        if (n.cur == root) {    // 현재 노드와 입력받은 노드가 같다면 갱신
            n.leftChild = (left == '.' ? null : new Node(left,null,null));
            n.rightChild = (right == '.' ? null : new Node(right,null,null));
        }
        else {  // 다르다면 자식노드로 파고들어가서 갱신
            if(n.leftChild != null) insert(n.leftChild, root, left, right);
            if(n.rightChild != null) insert(n.rightChild, root, left, right);
        }
    }
    public static void preOrder(Node node){
        if (node == null) return;
        ans[0].append(node.cur);
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    public static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.leftChild);
        ans[1].append(node.cur);
        inOrder(node.rightChild);
    }

    public static void postOrder(Node node){
        if (node == null) return;
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        ans[2].append(node.cur);
    }

    static class Node{
        char cur;
        Node leftChild;
        Node rightChild;
        public Node(char c, Node l, Node r){
            this.cur = c;
            this.leftChild = l;
            this.rightChild = r;
        }
    }
}
