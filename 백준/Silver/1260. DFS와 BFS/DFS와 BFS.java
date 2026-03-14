import java.util.*;
import java.io.*;

public class Main{
    static int N, M, V;
    static List<Integer>[] graph;
    static boolean[] DFSvisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        DFSvisited = new boolean[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++)
            Collections.sort(graph[i]);

        DFS(V);
        System.out.println();
        System.out.println(BFS(V));
    }

    static String BFS(int start){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();

        q.add(start); visited[start] = true;

        while (!q.isEmpty()){
            int cur = q.peek();
            sb.append(q.poll()).append(' ');

            for (int next: graph[cur]){
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return sb.toString();
    }

    static void DFS(int node){
        DFSvisited[node] = true;
        System.out.print(node + " ");

        for (int next: graph[node]){
            if (DFSvisited[next]) continue ;
            DFS(next);
        }
    }

}