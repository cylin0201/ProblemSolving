import java.util.*;
import java.io.*;

public class Main{
    static Set<String> set = new TreeSet<String>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken(), flag = st.nextToken();

            if (flag.equals("enter"))
                set.add(name);
            else
                set.remove(name);
        }

        for (String elem: set)
            System.out.println(elem);
    }
}