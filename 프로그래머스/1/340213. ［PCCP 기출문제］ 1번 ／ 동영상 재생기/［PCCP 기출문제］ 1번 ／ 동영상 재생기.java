class Solution {
    public String solution(String video_len, String pos,
                           String op_start, String op_end, String[] commands) {
        int maxTime = convertToSec(video_len);
        int currentTime = convertToSec(pos);
        int opStartTime = convertToSec(op_start);
        int opEndTime = convertToSec(op_end);
                    
        for (String command: commands){
            if (opStartTime <= currentTime && currentTime <= opEndTime){
                currentTime = opEndTime;
            }
            
            if (command.equals("prev")){
                if (currentTime < 10){
                    currentTime = 0;
                    continue;
                }
                currentTime -= 10;
            }
            else if (command.equals("next")){
                if (currentTime + 10 > maxTime){
                    currentTime = maxTime;
                    continue;
                }
                currentTime += 10;
            }
        }
        
        if (opStartTime <= currentTime && currentTime <= opEndTime){
                currentTime = opEndTime;
        }
        return convertToDt(currentTime);
    }
    
    static int convertToSec(String time){
        int res = 0;
        res += Integer.parseInt(time.substring(0, 2)) * 60;
        res += Integer.parseInt(time.substring(3, 5));
        
        return res;
    }
    
    static String convertToDt(int time){
        String res = "";
        int h = time / 60;
        if (h < 10) res += "0";
        res += h;
        
        res += ":";
        
        int m = time % 60;
        if (m < 10) res += "0";
        res += m;
        return res;
    }
}