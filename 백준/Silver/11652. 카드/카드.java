import java.util.*;
import java.io.*;

class Main{
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        Map <Long, Integer> map = new HashMap<>();

        for (int i = 0 ; i < n; i++){
            long input = Long.parseLong(br.readLine());
            map.put(input, map.getOrDefault(input, 0) + 1);
        }

        Long maxCard = 0L; int maxCnt = 0;
        for (Map.Entry<Long, Integer> entry: map.entrySet()){
            if (entry.getValue() > maxCnt) {
                maxCnt = entry.getValue();
                maxCard = entry.getKey();
            }
            else if (entry.getValue() == maxCnt) {
                maxCard = Math.min(maxCard, entry.getKey());
            }
        }

        System.out.println(maxCard);

    }
}