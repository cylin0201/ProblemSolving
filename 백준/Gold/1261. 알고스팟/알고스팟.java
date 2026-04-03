import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int[][] map, dist;
    static int INF = (int)1e9;

    static int[] dx = {-1 ,1, 0, 0};
    static int[] dy = {0 ,0, -1, 1};

    static class Edge{
        int x, y, d;
        Edge(int x,int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];
        for (int i = 0 ; i < N; i++)
            Arrays.fill(dist[i], INF);

        for (int i = 0 ; i < N; i++){
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++){
                map[i][j] = input.charAt(j) - '0';
            }
        }

        dijkstra(0, 0);

        System.out.println(dist[N - 1][M - 1]);
    }

    static void dijkstra(int x, int y){
        dist[x][y] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.d, o2.d));

        pq.offer(new Edge(x, y, dist[x][y]));
        while (!pq.isEmpty()){
            int cx = pq.peek().x, cy = pq.peek().y, cd = pq.peek().d; pq.poll();

            if (dist[cx][cy] < cd) continue;

            for (int i = 0 ; i < 4; i++){
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                int nd = cd + map[nx][ny];

                if (dist[nx][ny] > nd){
                    dist[nx][ny] = nd;
                    pq.offer(new Edge(nx, ny, nd));
                }
            }
        }
    }

}