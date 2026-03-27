import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> s = new Stack<>();
        
        s.push(arr[0]);
        for (int i = 1 ; i < arr.length; i++){
            if (s.peek() == arr[i]) continue;
            s.push(arr[i]);
        }
        int[] res = new int[s.size()];
        int i = 0;
        while (!s.isEmpty()){
            res[i] = s.peek(); s.pop();
            i++;
        }
        return reverse(res);
    }
    
    static int[] reverse(int[] arr){
        int[] res = new int[arr.length];
        
        for (int i = 0 ; i < arr.length; i++){
            res[arr.length - 1 - i] = arr[i];
        }
       return res; 
    }
}