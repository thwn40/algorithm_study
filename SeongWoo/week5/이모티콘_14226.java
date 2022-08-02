package SeongWoo.week5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 이모티콘_14226 {

    public static class Node{
        int time;
        int clip;
        int screen;
        boolean check = false;

        public void setNode(int time, int clip, int screen) {
            this.time = time;
            this.clip = clip;
            this.screen = screen;
            this.check = true;
        }
    }

    public static int bfs(Node[][] nodes, int target) {
        Queue<Node> queue = new LinkedList<>();
        nodes[0][1].setNode(0, 0, 1);
        queue.offer(nodes[0][1]);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            int nextTime = node.time + 1;   //다음 시간
            int pastedScreen = node.screen + node.clip;   //붙여넣어진 화면
            int copiedClip = node.screen;   //복사된 클립보드
            int deletedScreen = node.screen - 1;   //삭제된 화면

            //복사&저장
            if (copiedClip < nodes.length && !nodes[copiedClip][node.screen].check) {
                nodes[copiedClip][node.screen].setNode(nextTime, copiedClip, node.screen);
                queue.offer(nodes[copiedClip][node.screen]);
            }

            //붙여넣기
            if (pastedScreen < nodes[0].length && !nodes[node.clip][pastedScreen].check && node.clip != 0) {
                if (pastedScreen == target) {
                    return nextTime;
                }
                nodes[node.clip][pastedScreen].setNode(nextTime, node.clip, pastedScreen);
                queue.offer(nodes[node.clip][pastedScreen]);
            }

            //삭제
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
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        int max = target + 1;   //항상 clip 또는 screen이 target보다 작은범위에서 답을 구할 수 있다.

        Node[][] nodes = new Node[max][max];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                nodes[i][j] = new Node();
            }
        }

        System.out.println(bfs(nodes, target));
    }
}
