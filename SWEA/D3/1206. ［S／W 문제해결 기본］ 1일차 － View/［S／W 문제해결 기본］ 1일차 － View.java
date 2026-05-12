import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++){
            int res = 0;
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            for (int i = 2; i < N - 2; i++) {
                int cur = arr[i];
                int prev = arr[i - 1], pprev = arr[i - 2];
                int next = arr[i + 1], nnext = arr[i + 2];

                int temp = Math.min(Math.min(cur - prev, cur - pprev),
                        Math.min(cur - next, cur - nnext));

                if (temp > 0) res += temp;
            }
            System.out.println("#" + t + " " + res);
        }
    }
}