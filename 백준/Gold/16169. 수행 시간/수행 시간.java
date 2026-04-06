import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int[] c, t;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        c = new int[n + 1];
        t = new int[n + 1];
        dp = new long[n + 1];

        List<Integer>[] rankList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) rankList[i] = new ArrayList<>();

        int maxRank = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            c[i] = Integer.parseInt(st.nextToken());
            t[i] = Integer.parseInt(st.nextToken());

            rankList[c[i]].add(i);
            maxRank = Math.max(maxRank, c[i]);
        }

        for (int i : rankList[1]) {
            dp[i] = t[i];
        }

        for (int r = 2; r <= maxRank; r++) {
            for (int j : rankList[r]) {

                long start = 0;

                for (int i : rankList[r - 1]) {
                    long arrival = dp[i] + (long)(i - j) * (i - j);
                    start = Math.max(start, arrival);
                }

                dp[j] = start + t[j];
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}