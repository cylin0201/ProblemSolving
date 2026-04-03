import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int[] deg, time, dp;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        deg = new int[N + 1];
        time = new int[N + 1];
        dp = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            for (int j = 0 ; j < n; j++){
                int node = Integer.parseInt(st.nextToken());
                graph[node].add(i);
                deg[i]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++){
            if (deg[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()){
            int cur = q.poll();
            dp[cur] += time[cur];

            for (int next: graph[cur]){
                dp[next] = Math.max(dp[next], dp[cur]);
                deg[next] --;
                if (deg[next] == 0){
                    q.offer(next);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <=N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}