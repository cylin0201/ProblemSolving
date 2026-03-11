import java.util.*;
import java.io.*;

public class Main{
    static String s;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        n = Integer.parseInt(br.readLine());

        System.out.println(s.charAt(n - 1));
    }
}