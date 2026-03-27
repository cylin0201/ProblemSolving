import java.util.*;

class Solution {
    boolean solution(String s) {
        if (s.length() % 2 == 1) return false;
        
        Stack <Character> stack = new Stack<>();
        
        for (int i = 0 ; i < s.length(); i++){
            if (s.charAt(i) == '(') stack.push(s.charAt(i));
            else {
                if (stack.isEmpty()) return false;
                
                if (stack.peek() == '(') stack.pop();
                else stack.push(s.charAt(i));
            }
        }
    
        if (stack.isEmpty()) return true;
        return false;
    }
}