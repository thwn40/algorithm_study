package Inhwan.week5;

import java.util.*;

public class 이모티콘_14226 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        boolean[][] V = new boolean[S+1][S+1];

        Queue<Emoticon> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        q1.add(new Emoticon(1,0));
        V[1][0]=true;

        int count = 0;

        while (!q1.isEmpty()) {

            Emoticon now = q1.poll();
            int s = now.screen, c = now.clipboard;

            if (s==S) break;

            if (s!=c && !V[s][s]) {
                V[s][s]=true;
                q2.add(now.copy());
            }
            if (c>0 && s+c<=S && !V[s+c][c]) {
                V[s+c][c]=true;
                q2.add(now.paste());
            }
            if (s>0 && !V[s-1][c]) {
                V[s-1][c]=true;
                q2.add(now.delete());
            }

            if (q1.isEmpty()) {
                q1=q2;
                q2= new LinkedList<>();
                count++;
            }
        }

        System.out.println(count);
    }
}

class Emoticon {
    int screen;
    int clipboard;

    Emoticon (int screen, int clipboard){
        this.screen = screen;
        this.clipboard = clipboard;
    }

    Emoticon copy() {
        return new Emoticon(screen, screen);
    }

    Emoticon paste() {
        return new Emoticon(screen+clipboard, clipboard);
    }

    Emoticon delete() {
        return new Emoticon(screen-1,clipboard);
    }
}




//public class 이모티콘_14226 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int S = sc.nextInt();
//        boolean[][] V = new boolean[S+1][S+1];
//
//        Queue<int[]> q1 = new LinkedList<>(), q2 = new LinkedList<>();
//        q1.add(new int[] {1,0});
//        V[1][0]=true;
//
//        int c = 0;
//        while (!q1.isEmpty()) {
//            int[] now = q1.poll();
//            int a=now[0], b=now[1];
//            if (a == S) break;
//
//            if (a!=b && !V[a][a]) {
//              V[a][a]=true;
//              q2.add(new int[]{a, a});
//            }
//            if (b>0 && a+b<=S && !V[a+b][b]) {
//              V[a+b][b]=true;
//              q2.add(new int[]{a+b, b});
//            }
//            if (a>0 && !V[a-1][b]) {
//              V[a-1][b]=true;
//              q2.add(new int[]{a-1, b});
//            }
//
//            if (q1.isEmpty()) {
//                c++;
//                for (int[] arr : q2) q1.add(arr);
//            }
//        }
//        System.out.println(c);
//    }
//}