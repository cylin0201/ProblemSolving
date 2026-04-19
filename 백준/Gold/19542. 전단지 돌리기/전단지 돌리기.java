import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] edges;
    static int n, s, d;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n+1];
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            edges[x].add(y);
            edges[y].add(x);
        }
        visited[s] = true;
        dfs(s);
        System.out.println(Math.max(0, (ans-1)*2));
    }

    static int dfs(int cur) {
        int max = 0;
        for (int next : edges[cur]) {
            if (visited[next]) continue;
            visited[next] = true;
            max = Math.max(max, dfs(next));
        }
        if (max>=d) ans++;
        return max+1;
    }
}