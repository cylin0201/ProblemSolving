import java.util.*;
import java.io.*;

class Main{
    static int T, N, K;
    static int[] D;
    static int X, Y, W;
    static List<Integer>[] graph;
    static int[] deg;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while (T --> 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            D = new int[N + 1];
            deg = new int[N + 1];
            dp = new int[N + 1];
            graph = new List[N + 1];
            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());

                graph[X].add(Y);
                deg[Y]++;
            }

            W = Integer.parseInt(br.readLine());

            //위상 정렬 뽑아내기
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                if (deg[i] == 0) q.offer(i);
                dp[i] = D[i];
            }

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int next : graph[cur]) {
                    deg[next]--;
                    dp[next] = Math.max(dp[next], dp[cur] + D[next]);

                    if (deg[next] == 0)
                        q.offer(next);
                }
            }

            System.out.println(dp[W]);
        }
    }
}