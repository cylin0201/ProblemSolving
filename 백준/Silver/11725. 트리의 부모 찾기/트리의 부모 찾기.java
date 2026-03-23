import java.util.*;
import java.io.*;

class Main{
    static int N;
    static List<Integer>[] tree;
    static int[] parents;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        parents = new int[N + 1];

        for (int i = 1 ; i <= N; i++)
            tree[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            tree[x].add(y);
            tree[y].add(x);
        }

        BFS(1);

        for (int i = 2; i<= N; i++)
            System.out.println(parents[i]);
    }

    static void BFS(int node) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[node] = true;
        q.offer(node);

        while (!q.isEmpty()) {
            int cur = q.peek(); q.poll();
            for (int next : tree[cur]) {
                if (visited[next]) continue;

                parents[next] = cur;
                visited[next] = true;
                q.offer(next);
            }
        }
    }
}