import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] days = new int[speeds.length];
        
        for (int i = 0 ; i < days.length; i++){
            days[i] = solve(progresses[i], speeds[i]);
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < days.length; i++){
            int cnt = 1, j;
            for (j = i + 1; j < days.length; j++){
                if (days[i] < days[j]) break;
                cnt++;
            }
            list.add(cnt);
            i = j - 1;
        }
        
        int[] res = new int[list.size()];
        for (int i = 0 ; i < list.size(); i++){
            res[i] = list.get(i);
        }
        
        return res;
    }
    
    static int solve(int a, int b){
        int day = 0;
        while (true){
            if (day * b + a >= 100) 
                break;
            day++;
        }
        return day;
    }
}