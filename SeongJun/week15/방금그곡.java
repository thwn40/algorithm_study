package SeongJun.week15;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class 방금그곡 {
    /**
     * 네오는 자신이 기억한 멜로디를 가지고 방금그곡을 이용해 음악을 찾는다.
     * 들은 멜로디랑, 실제 악보, 방송국에서 재생한 시간
     * 1.파싱
     * 2.실제로 방송국에서 방송된 멜로디
     * 3.포함 되있는지 검사
     */
    public static String solution(String m, String[] melodyInfo) throws ParseException {

        int maxPlayTime = -1;
        String answer="(None)";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        m=newMelody(m);

        int playtime =0;
        for (String musicinfo : melodyInfo) {
            String[] split = musicinfo.split(",");

            String title = split[2];

            Date start = format.parse(split[0]);
            Date end = format.parse(split[1]);

            //실행시간
            playtime = (int) ((end.getTime()- start.getTime())/60000);
            System.out.println("playtime = " + playtime);


            split[3]=newMelody(split[3]);


            //실제 방송국에서 재생된 멜로디
            String realMelodyInfo;

            // 곡을 여러번 튼 경우
            if(playtime>split[3].length()){
                realMelodyInfo = split[3].repeat((playtime/split[3].length()))+split[3].substring(0, (playtime%split[3].length()));
            }

            //중간에 끊을 경우
            else{
                realMelodyInfo = split[3].substring(0,  playtime);
            }

            System.out.println("melodyinfo = " + realMelodyInfo);


            //
            if(realMelodyInfo.contains(m)&&playtime>maxPlayTime){
                answer=title;
                maxPlayTime=playtime;
            }
        }

        return answer;
    }

    public static String newMelody(String string){
        string=string.replace(("C#"), "c");
        string=string.replace(("D#"), "d");
        string=string.replace(("F#"), "f");
        string=string.replace(("G#"), "g");
        string=string.replace(("A#"), "a");

        return string;
    }

    public static void main(String[] args) throws ParseException {
        방금그곡.solution("ABC",new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"});
    }
}

