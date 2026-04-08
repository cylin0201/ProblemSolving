import java.util.*;
import java.io.*;

class Main {
    static int N;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int g = gcd(x, y);
            StringBuilder sb = new StringBuilder();
            String key = sb.append(x/g).append(',').append(y/g).toString();

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int max = -1;
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            max = Math.max(max, entry.getValue());
        }
        System.out.println(max);
    }

    static int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }
}