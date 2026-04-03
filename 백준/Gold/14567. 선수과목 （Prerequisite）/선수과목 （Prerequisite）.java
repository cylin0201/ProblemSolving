import java.util.*;
import java.io.*;

class Main{
    static int n, m;
    static int a, b;
    static int sem = 1;
    static List<Integer>[] graph;
    static int[] deg;
    static Queue<Integer> q = new ArrayDeque<>();
    static Queue<Integer> wQ = new ArrayDeque<>();
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        deg = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();


        for (int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            deg[b]++;
        }

        for (int i = 1; i <= n; i++){
            if (deg[i] == 0) q.offer(i);
        }

        res = new int[n + 1];

        while (true) {
            while (!q.isEmpty()) {
                int cur = q.poll();
                res[cur] = sem;

                for (int next : graph[cur]) {
                    deg[next]--;
                    if (deg[next] == 0) wQ.offer(next);
                }
            }
            sem++;

            while (!wQ.isEmpty()){
                q.offer(wQ.poll());
            }

            if (q.isEmpty()) break;
        }

        for (int i = 1; i <= n; i++)
            System.out.print(res[i] + " ");
    }
}