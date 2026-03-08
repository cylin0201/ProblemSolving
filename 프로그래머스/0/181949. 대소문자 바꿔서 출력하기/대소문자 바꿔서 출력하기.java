import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        
        for (int i = 0 ; i < a.length(); i++){
            char c = a.charAt(i);
            
            if (c >= 'A' && c <= 'Z')
                c += 'a' - 'A';
            else
                c -= 'a' - 'A';
            
            System.out.print(c);
        }
    }
}