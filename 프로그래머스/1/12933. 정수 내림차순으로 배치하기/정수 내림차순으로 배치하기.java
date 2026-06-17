import java.util.*;

class Solution {
    static long res;
    public long solution(long n) {
        String s = Long.toString(n);
        int[] arr = new int[s.length()];
        
        for (int i = 0; i < arr.length; i++){
            arr[i] = s.charAt(i) - '0';
        }
        Arrays.sort(arr);
        
        for (int i = arr.length - 1; i >= 0 ; i--){
            res += arr[i];
            res *= 10;
        }
        
        return res / 10;
        
        
        
    }
}