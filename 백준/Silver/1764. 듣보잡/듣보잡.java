import java.util.*;
import java.io.*;

public class Main{
    static int n, m;
    static Map<String, Integer> map = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < n; i++) {
            String key = br.readLine();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (int i = 0 ; i < m; i++) {
            String key = br.readLine();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            if (entry.getValue() == 2) {
                cnt++;
                sb.append(entry.getKey());
                sb.append('\n');
            }
        }

        System.out.println(cnt);
        System.out.print(sb.toString());
    }
}