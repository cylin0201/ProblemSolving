import java.util.*;
import java.io.*;

public class Main{
    static int n, m;
    static Map<String, Integer> nameMap = new HashMap<>();
    static Map<Integer, String> orderMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1 ; i <= n; i++) {
            String input = br.readLine();
            nameMap.put(input, i);
            orderMap.put(i, input);
        }
        for (int i = 0; i < m; i++){
            String input = br.readLine();
            if ('1' <= input.charAt(0) && input.charAt(0) <= '9')
                System.out.println(orderMap.get(Integer.parseInt(input)));
            else
                System.out.println(nameMap.get(input));
        }

    }
}