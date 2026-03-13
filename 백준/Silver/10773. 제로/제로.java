import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> s = new Stack<>();
        for (int i = 0 ; i < n; i++){
            int input = Integer.parseInt(br.readLine());
            if (input != 0) s.push(input);
            else s.pop();
        }

        int sum = 0;
        List <Integer> list = new ArrayList<>(s);
        for (Integer elem: list)
            sum += elem;

        System.out.println(sum);
    }
}