package SeongWoo.week5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 이모티콘_14226 {

    public static class Node{
        //해당 노드까지 걸리는 시간.
        int time;
        //클립보드에 저장된 이모티콘의 개수.
        int clip;
        //화면에 나타나는 이모티콘의 개수
        int screen;
        //노드가 탐색되었는지 판단하는 flag
        boolean check = false;

        public void setNode(int time, int clip, int screen) {
            this.time = time;
            this.clip = clip;
            this.screen = screen;
            this.check = true;
        }
    }

    //bfs를 사용해서 node를 탐색하는 메서드,
    //screen이 target과 같아지는 경우를 탐색하면 즉시 해당 노드의 time을 반환한다.
    public static int bfs(Node[][] nodes, int target) {
        Queue<Node> queue = new LinkedList<>();
        nodes[0][1].setNode(0, 0, 1);
        queue.offer(nodes[0][1]);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            //다음 시간
            int nextTime = node.time + 1;
            //붙여넣어진 화면
            int pastedScreen = node.screen + node.clip;
            //복사된 클립보드
            int copiedClip = node.screen;
            //삭제된 화면
            int deletedScreen = node.screen - 1;

            //복사&저장
            //복사된 clip이 이차배열의 크기보다 작거나, 해당 노드가 탐색되지 않았을 경우 시행한다.
            if (copiedClip < nodes.length && !nodes[copiedClip][node.screen].check) {
                nodes[copiedClip][node.screen].setNode(nextTime, copiedClip, node.screen);
                queue.offer(nodes[copiedClip][node.screen]);
            }

            //붙여넣기
            //붙여넣어진 screen이 이차배열의 크기보다 작거나, 해당 노드가 탐색되지 않았거나, clip에 이모티콘이 1개 이상일 경우 시행된다.
            if (pastedScreen < nodes[0].length && !nodes[node.clip][pastedScreen].check && node.clip != 0) {
                if (pastedScreen == target) {
                    return nextTime;
                }
                nodes[node.clip][pastedScreen].setNode(nextTime, node.clip, pastedScreen);
                queue.offer(nodes[node.clip][pastedScreen]);
            }

            //삭제
            //이모티콘이 삭제된 화면의 이모티콘이 0이상이거나, 해당 노드가 탐색되지 않았을 경우 시행한다.
            if (deletedScreen >= 0 && !nodes[node.clip][deletedScreen].check) {
                if (deletedScreen == target) {
                    return nextTime;
                }
                nodes[node.clip][deletedScreen].setNode(nextTime, node.clip, deletedScreen);
                queue.offer(nodes[node.clip][deletedScreen]);
            }
        }
        return -1;
   }

    public static void main(String[] args) {
        //초기화 시작
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        //항상 clip 또는 screen이 target보다 작은범위에서 답을 구할 수 있다.
        int max = target + 1;

        /*
	    row = clip, col = screen이라하고
        해당 row,col(=clip, screen)의 node가 탐색되었다면 탐색하지 않는다.
        */
        Node[][] nodes = new Node[max][max];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                nodes[i][j] = new Node();
            }
        }
        //초기화 끝끝
        System.out.println(bfs(nodes, target));
    }
}
