public class Solution {
    static int res;
    public int solution(int n) {
        getDigits(n);
        
        return res;
    }
    
    static void getDigits(int num){
        if (num == 0) return;
        res += num % 10;
        getDigits(num / 10);
    }
}