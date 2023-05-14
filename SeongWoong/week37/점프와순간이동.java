package summerWinterCodingTo2018;

public class 점프와순간이동 {
    public static void main(String[] args) {

    }
    public class Solution {
        public int solution(int n) {
            int ans = 1;
            while(n!=1){
                if(n%2 ==1) ans++;
                n/=2;
            }
            return ans;
        }
    }
}
