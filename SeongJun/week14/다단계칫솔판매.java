package SeongJun.week14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class 다단계칫솔판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        HashMap<String,Member> hashMap = new LinkedHashMap<>();
        Member center = new Member("center");
        hashMap.put("center", center);
        for (int i = 0; i < enroll.length; i++) {
            hashMap.put(enroll[i],new Member(enroll[i]));
            if(referral[i].equals("-")){
                center.addSubscriber(new Member(enroll[i]));
                hashMap.get(enroll[i]).setPublisher(hashMap.get("center"));
            }
            else{
                hashMap.get(enroll[i]).setPublisher(hashMap.get(referral[i]));
                hashMap.get(referral[i]).addSubscriber(hashMap.get(enroll[i]));
            }
        }

        for (int i = 0; i < amount.length; i++) {
            Member member = hashMap.get(seller[i]);
            member.price=member.price+amount[i]*100;
            // publisher = edward
            int commission = (int) (amount[i]*100);// 120
            while(member.publisher!=null){
                commission = (int) (commission*0.1);
                if(commission<1){
                    commission=0;
                }
                    member.price= (member.price-commission);
                    member.publisher.price=  (member.publisher.price+commission);

                member=member.publisher;

            }

        }
        int index = 0;
        hashMap.remove("center");

        for (String s : hashMap.keySet()) {
            answer[index]=hashMap.get(s).price;
            index++;
        }


        return answer;

    }

    static class Member{
        private String name;
        private Member publisher;
        private ArrayList<Member> subscriber = new ArrayList<>();
        private int price;


        public Member(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }


        public void addSubscriber(Member member){
           this.subscriber.add(member);
        }

        public void setPublisher(Member member){
            this.publisher = member;
        }

        @Override
        public String toString() {
            return
                    name + " " + "price = " + price;
        }
    }

    public static void main(String[] args) {
        다단계칫솔판매 s = new 다단계칫솔판매();
        System.out.println(Arrays.toString(s.solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10})));

    }
}

