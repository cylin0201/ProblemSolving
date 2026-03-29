import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int[][] m;
    static int[][] dist;
    static int INF = (int)1e9;
    static int t = 1;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Edge{
        int x, y, d;
        Edge(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) return ;

            m = new int[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; i++)
                Arrays.fill(dist[i], INF);

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    m[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra(0, 0);

            System.out.println("Problem " + t + ": "+ dist[N - 1][N - 1]);
            t++;
        }
    }

    static void dijkstra(int sx, int sy){
        dist[sx][sy] = m[sx][sy];
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.d, o2.d));
        pq.offer(new Edge(sx, sy, m[sx][sy]));

        while (!pq.isEmpty()){
            int cx = pq.peek().x, cy = pq.peek().y, distance = pq.peek().d; pq.poll();

            if (dist[cx][cy] < distance) continue;

            for (int i = 0 ; i < 4; i++){
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;

                int nextDistance = distance + m[nx][ny];
                if (dist[nx][ny] > nextDistance){
                    dist[nx][ny] = nextDistance;
                    pq.offer(new Edge(nx, ny, nextDistance));
                }
            }
        }
    }

}