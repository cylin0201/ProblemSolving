import java.util.*;
import java.io.*;

public class Main{
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                System.out.println(pq.isEmpty() ? 0 : pq.peek());
                pq.poll();
            }
            else pq.add(input);
        }
    }
}