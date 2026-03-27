import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0 ; i < scoville.length; i++)
            pq.offer(scoville[i]);
        
        int res = 0;
        while (pq.peek() < K){
            if (pq.size() < 2) return -1;
            
            int first = pq.poll(), second = pq.poll();
            pq.offer(first + 2 * second);
            
            res++;
        }
        return res;
    }
}