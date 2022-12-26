package Inhwan.week16;

import java.util.Arrays;
import java.util.Comparator;

class 방금그곡 {
    public String solution(String m, String[] musicinfos) {
        String M = deleteSharp(m);
        try {
            return Arrays.stream(musicinfos)
                    .map(musicInfo -> parsing(musicInfo, M.length()))
                    .sorted(Comparator.comparing(info -> info.start))
                    .sorted(Comparator.comparing(info -> info.time * -1))
                    .filter(info -> contains(info.music, M))
                    .findFirst().get().name;
        } catch(Exception e) {
            return "(None)";
        }
    }

    class Info {
        int start;
        int end;
        int time;
        String name;
        String music;

        Info(int start, int end, String name, String music) {
            this.start = start;
            this.end = end;
            this.time = end - start;
            this.name = name;
            this.music = music;
        }
    }

    public Info parsing(String musicInfo, int l) {
        int start = 60 * Integer.parseInt(musicInfo.substring(0,2))
                + Integer.parseInt(musicInfo.substring(3,5));

        int end = 60 * Integer.parseInt(musicInfo.substring(6,8))
                + Integer.parseInt(musicInfo.substring(9,11));

        int time = end - start;

        int i = 12;
        while (musicInfo.charAt(i) != ',') {
            i++;
        }

        String name = musicInfo.substring(12, i);

        String str = deleteSharp(musicInfo.substring(i+1));
        String music = str;

        while (music.length() < time) {
            music += str;
        }
        music = music.substring(0, time);

        return new Info(start, end, name, music);
    }

    boolean contains(String str1, String str2) {
        int n = str1.length() - str2.length() + 1;
        int l = str2.length();

        for (int i = 0; i < n; i++) {
            if (str1.substring(i, i+l).equals(str2)) return true;
        }

        return false;
    }

    String deleteSharp(String music) {
        String result = "";
        int i = 0;

        while (i < music.length()) {
            if (i == music.length()-1) {
                result += music.charAt(i);
                i++;
            } else if (music.charAt(i+1) == '#') {
                result += music.substring(i, i+1).toLowerCase();
                i += 2;
            } else {
                result += music.charAt(i);
                i++;
            }
        }

        return result;
    }
}