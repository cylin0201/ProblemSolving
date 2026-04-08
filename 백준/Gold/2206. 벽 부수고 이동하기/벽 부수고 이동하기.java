import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] m;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1} ;

    static class Node{
        int x, y, dist, hasBroken;
        Node(int x, int y, int dist, int hasBroken){
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.hasBroken = hasBroken;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        m = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1][2];

        for (int i = 0 ; i < N; i++){
            String input = br.readLine();
            for (int j = 0 ; j < M; j++){
                m[i + 1][j + 1] = input.charAt(j) - '0';
            }
        }

        System.out.println(BFS(1, 1));
    }

    static int BFS(int x,int y){
        visited[x][y][0] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, y, 1, 0));

        while (!q.isEmpty()){
            Node c = q.poll();

            if (c.x == N && c.y == M)
                return c.dist;

            for (int i = 0; i < 4; i++){
                int nx = c.x + dx[i], ny = c.y + dy[i];
                if (nx < 1 || nx > N || ny < 1 || ny > M)
                    continue;
                int nFlag = c.hasBroken;
                if (m[nx][ny] == 1){ //다음 칸이 벽일 때
                    if (c.hasBroken == 1) continue;
                    nFlag = 1;
                }
                if (visited[nx][ny][nFlag])
                    continue;

                visited[nx][ny][nFlag] = true;
                q.offer(new Node(nx, ny, c.dist + 1, nFlag));
            }
        }
        return -1;
    }
}