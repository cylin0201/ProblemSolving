class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        StringBuffer sb = new StringBuffer();
        
        int len = 0;
        for (int i = 0 ; i < s; i++, len++)
            sb.append(my_string.charAt(i));
        for (int i = 0 ; i < overwrite_string.length(); i++, len++)
            sb.append(overwrite_string.charAt(i));
        for (int i = len; i < my_string.length(); i++)
            sb.append(my_string.charAt(i));
        
        return sb.toString();
    }
}