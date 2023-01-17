public class 카카오2022인턴십_성격유형검사하기 {
    class Solution {
        public String solution(String[] survey, int[] choices) {
            String answer = "";

            int[] arr = new int[8];

            for(int i=0;i<survey.length;i++){
                String result = getResult(survey[i],choices[i]);
                if(result.equals("")) continue;
                arr[result.charAt(0)-'0'] += result.charAt(1)-'0';
            }

            return getAnswer(arr);
        }

        public String getResult(String survey, int choice){
            String ans = "";
            if(choice==4) return ans;
            if(choice<4) {
                ans += getInt(survey.charAt(0)) +""+ (4-choice);
            }else if(choice>4){
                ans += getInt(survey.charAt(1)) +""+ (choice-4);
            }
            return ans;
        }

        public int getInt(char ch){
            switch (ch){
                case 'R': return 0;
                case 'T': return 1;
                case 'C': return 2;
                case 'F': return 3;
                case 'J': return 4;
                case 'M': return 5;
                case 'A': return 6;
                case 'N': return 7;
            }
            return 0;
        }

        public String getAnswer(int[] arr){
            String answer = "";
            for (int i=0;i<4;i++){
                if(arr[2*i]>=arr[2*i+1]){
                    answer += 2*i;
                }else{
                    answer += 2*i+1;
                }
            }
            answer = answer.replace("0","R").replace("1","T").replace("2","C").replace("3","F").replace("4","J").replace("5","M").replace("6","A").replace("7","N");
            return answer;
        }
    }
}
