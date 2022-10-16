package SeongWoong.week14;

public class DEV2021상반기_로또의최고순위와최저순위 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int matching = 0;
        int blank = 0;

        for(int i = 0; i < 6; i++){
            if(lottos[i] == 0){
                blank++;
            }
            for(int j = 0; j < 6; j++){
                if(lottos[i] == win_nums[j]){
                    matching++;
                    break;
                }
            }
        }
        int highRank = 7 - (matching + blank);
        int lowRank = 7 - matching;

        if (lowRank == 7) lowRank = 6;
        if (highRank == 7) highRank = 6;


        int[] answer = new int[]{highRank, lowRank};

        return answer;
    }
}