class Solution {
    public int solution(int[][] sizes) {
        int temp, maxX = 0, maxY = 0;
        
        for (int i = 0 ; i < sizes.length; i++){
            if (sizes[i][0] < sizes[i][1]){
                temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
                
                maxX = Math.max(maxX,sizes[i][0]);
                maxY = Math.max(maxY,sizes[i][1]);
        }
            System.out.println(maxX + " " + maxY);
        return maxX * maxY;
        
    }
}