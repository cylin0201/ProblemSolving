import java.util.*;
import java.io.*;

public class Main{
    static Deque<Integer> dq = new ArrayDeque<>();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            if (input == 1)
                dq.addFirst(Integer.parseInt(st.nextToken()));
            else if (input == 2)
                dq.addLast(Integer.parseInt(st.nextToken()));
            else if (input == 3){
                if (dq.isEmpty()) sb.append(-1).append('\n');
                else  sb.append(dq.pollFirst()).append('\n');
            }
            else if (input == 4){
                if (dq.isEmpty()) sb.append(-1).append('\n');
                else  sb.append(dq.pollLast()).append('\n');
            }
            else if (input == 5)
                sb.append(dq.size()).append('\n');
            else if (input == 6){
                int x = dq.isEmpty() ? 1: 0;
                sb.append(x).append('\n');
            }
            else if (input == 7){
                int x = dq.isEmpty() ? -1 : dq.getFirst();
                sb.append(x).append('\n');
            }
            else{
                int x = dq.isEmpty() ? -1 : dq.getLast();
                sb.append(x).append('\n');
            }
        }
        System.out.println(sb.toString());
    }
}