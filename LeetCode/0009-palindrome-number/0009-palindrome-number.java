class Solution {
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);

        int s = 0, e = str.length() - 1;

        while (s < e){
            if (str.charAt(s) != str.charAt(e))
                return false;
            s++; e--;
        }
        return true;
    }
}