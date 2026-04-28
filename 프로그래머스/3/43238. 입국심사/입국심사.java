import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long res = 0;
        Arrays.sort(times);
        
        long s = 0, e = times[times.length - 1] * (long)n;
        
        while (s <= e){
            long mid = (s + e) / 2;
            long complete = 0;
            for (int i = 0 ; i < times.length; i++){
                complete += mid / times[i];
            }
            if (complete < n) s = mid + 1;
            else{
                e = mid - 1;
                res = mid;
            } 
        }
        return res;
    }
}