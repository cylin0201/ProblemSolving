import java.util.*;
import java.io.*;

public class Main{
    static String s;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            s = br.readLine();
            if (s == null || s.isEmpty()) break;

            System.out.println(s);
        }
    }
}