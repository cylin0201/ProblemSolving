import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> s = new Stack<>();
        for (int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());

            if (input == 1)
                s.push(Integer.parseInt(st.nextToken()));
            else if (input == 2){
                if (s.isEmpty()) System.out.println(-1);
                else {
                    System.out.println(s.peek());
                    s.pop();
                }
            }
            else if (input == 3)
                System.out.println(s.size());
            else if (input == 4){
                int x = (s.isEmpty() ? 1 : 0);
                System.out.println(x);
            }
            else {
                int x = (s.isEmpty()? -1: s.peek());
                System.out.println(x);
            }
        }
    }
}