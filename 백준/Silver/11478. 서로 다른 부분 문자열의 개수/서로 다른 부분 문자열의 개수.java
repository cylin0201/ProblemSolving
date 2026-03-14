import java.util.*;
import java.io.*;

public class Main{
    static Set<String> set = new HashSet<>();
    static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        for (int i = 0 ; i < input.length(); i++){
            makeAndAddString(i);
        }

        System.out.println(set.size());
    }

    static void makeAndAddString(int num){
        StringBuilder sb = new StringBuilder();

        for (int i = num ; i < input.length(); i++){
            sb.append(input.charAt(i));
            set.add(sb.toString());
        }
    }
}