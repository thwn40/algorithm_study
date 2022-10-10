package Inhwan.week14;

import java.util.HashMap;
import java.util.Map;

class 다단계칫솔판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, Member> members = new HashMap<>();
        members.put("-", new Member());

        for (int i = 0; i < enroll.length; i++) {
            members.put(enroll[i], new Member(members.get(referral[i])));
        }

        for (int i = 0; i < seller.length; i++) {
            distribute(members.get(seller[i]), amount[i]);
        }

        for (int i = 0; i< enroll.length; i++) {
            answer[i] = members.get(enroll[i]).profit;
        }

        return answer;
    }

    class Member {
        Member recommender;
        int profit = 0;

        Member() {};
        Member(Member recommender){
            this.recommender = recommender;
        }
    }

    private void distribute(Member member, int amount) {
        int profit = amount*100;

        member.profit += profit;

        Member recommender = member.recommender;

        while (recommender != null) {
            profit /= 10;
            member.profit -= profit;
            recommender.profit += profit;

            member = recommender;
            recommender = member.recommender;
        }
    }
}