import java.util.*;

class Solution {
    static boolean[] visited;
    static int N;
    
    public int solution(String begin, String target, String[] words) {
        N = words.length;
        visited = new boolean[N];
        
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(begin, 0));
        
        int res = 0;        
        while (!q.isEmpty()){
            String cur = q.peek().str; int num = q.peek().num;
            q.poll();
            
            if (cur.equals(target))
                res = num;
            
            for (int i = 0; i< words.length; i++){
                if (!visited[i] && canChange(cur, words[i])){
                    visited[i] = true;
                    q.offer(new Node(words[i], num + 1));
                }
            }
        }
        return res;
    }
    
    //하나의 알파벳만이 차이 나는 지를 확인
    static boolean canChange(String from, String to){
        int cnt = 0, i = 0;
        while (i < from.length()){
            if (cnt > 1) return false;
            
            if (from.charAt(i) != to.charAt(i))
                cnt++;
            i++;
        }
        if (cnt == 1) return true;
        return false;
    }
    
    static class Node{
        String str; int num;
        
        Node(String str, int num){
            this.str = str;
            this.num = num;
        }
    }
}