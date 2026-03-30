import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] m, trash;
    static int[][] surm, surTrash;
    static int sx, sy, ex, ey;
    static int INF = (int)1e9;

    static int[] dx = {-1, 1, 0, 0}; //상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        m = new int[N][M];
        surm = new int[N][M];
        trash = new int[N][M];
        surTrash = new int[N][M];

        for (int i = 0 ; i < N; i++)
            Arrays.fill(trash[i], INF);

        for (int i = 0 ; i < N; i++)
            Arrays.fill(surTrash[i], INF);

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                if (c == '.') m[i][j] = 0;
                else if (c == 'g') m[i][j] = 1;
                else if (c == 'S') {sx = i; sy = j;}
                else {ex = i; ey = j;}
            }
        }

        for (int i = 0 ; i < N; i++){
            for (int j = 0 ; j < M; j++){
                if (m[i][j] == 1){
                    for (int dir = 0 ; dir < 4; dir++){
                        int ni = i + dx[dir], nj = j + dy[dir];
                        if (ni < 0 || ni >= N || nj < 0 || nj >= M || m[ni][nj] != 0)
                            continue;
                        surm[ni][nj] = 1;
                    }
                }
            }
        }

        dijkstra(sx, sy);

        System.out.println(trash[ex][ey] + " " + surTrash[ex][ey]);
    }

    static void dijkstra(int x, int y){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.t == o2.t) return Integer.compare(o1.s, o2.s);
            return Integer.compare(o1.t, o2.t);
        });

        trash[x][y] = m[x][y];
        surTrash[x][y] = 0;
        pq.offer(new Edge(x, y, trash[x][y], surTrash[x][y]));

        while (!pq.isEmpty()){
            int cx = pq.peek().x, cy = pq.peek().y, ct = pq.peek().t, cs = pq.peek().s;
            pq.poll();

            if (trash[cx][cy] < ct) continue;
            if (surTrash[cx][cy] < cs) continue;

            for (int i = 0 ; i < 4; i++){
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                int nt = ct + m[nx][ny];
                int ns;
                if (nx == ex && ny == ey) ns = cs;
                else ns = cs + surm[nx][ny];

                if (trash[nx][ny] == nt){
                    if (surTrash[nx][ny] > ns){
                        surTrash[nx][ny] = ns;
                        pq.offer(new Edge(nx, ny, nt, ns));
                    }
                }
                else if (trash[nx][ny] > nt){
                    trash[nx][ny] = nt;
                    surTrash[nx][ny] = ns;
                    pq.offer(new Edge(nx, ny, nt, ns));
                }
            }
        }
    }

    static class Edge{
        int x, y, t, s;
        Edge(int x, int y, int t, int s){
            this.x = x;
            this.y = y;
            this.t = t;
            this.s = s;
        }
    }
}