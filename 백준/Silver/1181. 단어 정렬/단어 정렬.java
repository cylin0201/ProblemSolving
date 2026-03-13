import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Set<String> s = new HashSet<>();

        for (int i = 0 ; i < n; i++)
            s.add(br.readLine());

        List<String> list = new ArrayList<>(s);

        Collections.sort(list, (o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2);
            return o1.length() - o2.length();
        });

        for (int i = 0 ; i < list.size(); i++)
            System.out.println(list.get(i));
    }
}