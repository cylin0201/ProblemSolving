class Solution {
    public int[] solution(long n) {
        String s = Long.toString(n);
        int[] arr = new int[s.length()]; 
        
        for (int i = 0; i < s.length(); i++){
            arr[i] = s.charAt(s.length() - 1 - i) - '0';
        }
        return arr;
    }
}