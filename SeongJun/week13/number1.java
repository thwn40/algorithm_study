package SeongJun.week13;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class number1 {
    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {
        HashMap<String, Integer> termHashMap = new HashMap<>();
        for (String term : terms) {
            String[] s = term.split(" ");
            termHashMap.put(s[0], Integer.valueOf(s[1]));

        }
        int[] answer = {};
        ArrayList<Integer> answerArray = new ArrayList<>();
        //몇일이 지났나 확인하고
        String[] todaySplit = today.split("\\.");


        int index =0;
        for (String privacy : privacies) {
            index++;

            String privacyDate = privacy.substring(0, 10);
            String privacyYak = privacy.substring(11,12);

            String[] privacyDateSplit = privacyDate.split("\\.");
            int todayYear = Integer.parseInt(todaySplit[0]);
            int todayMonth = Integer.parseInt(todaySplit[1]);
            int todayDay = Integer.parseInt(todaySplit[2]);
            int privacyDateYear = Integer.parseInt(privacyDateSplit[0]);
            int privacyDateMonth = Integer.parseInt(privacyDateSplit[1]);
            int privacyDateDay = Integer.parseInt(privacyDateSplit[2]);
            int day =0;
            int month =0;
            int year=0;
            //일빼기
            if (todayDay-privacyDateDay<0) {
                todayMonth=todayMonth-1;
                day=todayDay-privacyDateDay+28;
            }
            if(todayDay-privacyDateDay>0){
                day=todayDay-privacyDateDay;
            }
            //월 빼기
            if(todayMonth-privacyDateMonth<0){
                todayYear=todayYear-1;
                month=todayMonth-privacyDateMonth+12;
            }
            if(todayMonth-privacyDateMonth>0){
                month=todayMonth-privacyDateMonth;
            }
            //년 빼기
            year=todayYear-privacyDateYear;


            //지난 개월수
            int afterMonth = year*12+month;
            //유효기간
            Integer after = termHashMap.get(privacyYak);

            if(afterMonth-after>0){
                answerArray.add(index);
            }
            if(afterMonth==after&&day>=0){
                answerArray.add(index);
            }


        }

        //약관 종류를 확인해서 시간이 /28하고 시간이 지났으면 파기
        answer = answerArray.stream()
                .mapToInt(i -> i)
                .toArray();

        return answer;
    }
    public static void main(String[] args) throws ParseException {

//        Main main = new Main();
//        main.solution("2022.05.19",new String[]{"A 6","B 12","C 3"},new String[]{"2021.05.02 A","2021.07.01 B","2022.02.19 C","2022.02.20 C"});
//




    }

}

