import java.util.*;
import java.io.*;

class Main{
    static int N, V, E;
    static int u, v;

    static List<Integer>[] graph;
    static int[] color; // 0: 미방문, 1 / -1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        while (N --> 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            color = new int[V + 1];

            for (int i = 1; i <= V; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            boolean isBipartite = true;

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {
                    if (!BFS(i)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            System.out.println(isBipartite ? "YES" : "NO");
        }
    }

    static boolean BFS(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        color[start] = 1;

        while (!q.isEmpty()){
            int cur = q.poll();

            for (int next: graph[cur]){
                // 아직 방문 안 했으면 색 반대로
                if (color[next] == 0){
                    color[next] = -color[cur];
                    q.offer(next);
                }
                // 같은 색이면 실패 == 홀수 사이클 존재
                else if (color[next] == color[cur]){
                    return false;
                }
            }
        }
        return true;
    }
}