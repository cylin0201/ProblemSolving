import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        if (isEven(n)) System.out.println(n + " is even");
        else System.out.println(n + " is odd");
    }
    
    static boolean isEven(int n){
        if (n % 2 == 0) return true;
        return false;
    }
}