class Solution {
    static int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        DFS(0, numbers, target);
        return cnt;
    }
    
    static void DFS(int n, int[] numbers, int target){
        if (n == numbers.length){
            updateCnt(numbers, target);
            return ;
        }
        
        DFS(n + 1, numbers, target);
        numbers[n] *= -1;
        DFS(n + 1, numbers, target);
    }
    
    static void updateCnt(int[] numbers, int target){
        int sum = 0;
        for (int i = 0 ; i < numbers.length; i++){
            sum += numbers[i];
        }
        if (sum == target) cnt++;
    }
    
}