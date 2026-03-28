import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int[] score;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i<= N; i++)
            score[i] = Integer.parseInt(br.readLine());

        dp[1] = score[1];
        if (N >= 2)
            dp[2] = score[1] + score[2];

        for (int i = 3; i<=N; i++){
            dp[i] = Math.max(dp[i - 2] + score[i],
                             dp[i - 3] + score[i - 1] + score[i]);
        }

        System.out.println(dp[N]);
    }
}