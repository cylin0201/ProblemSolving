import java.util.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int testCaseNumber = Integer.parseInt(br.readLine());

            int[] count = new int[101];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 1000; i++) {
                int score = Integer.parseInt(st.nextToken());
                count[score]++;
            }

            int maxCount = 0;
            int answer = 0;

            for (int score = 0; score <= 100; score++) {
                if (count[score] >= maxCount) {
                    maxCount = count[score];
                    answer = score;
                }
            }

            sb.append("#")
                    .append(testCaseNumber)
                    .append(" ")
                    .append(answer)
                    .append("\n");
        }

        System.out.print(sb);
    }
}