import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int[][] m;
    static final int bar = 3;
    static List<Pos> safeList = new ArrayList<>();
    static int res = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Pos{
        int x, y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N][M];

        for (int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                m[i][j] = Integer.parseInt(st.nextToken());
                if (m[i][j] == 0) safeList.add(new Pos(i, j));
            }
        }

        for (int i = 0; i < safeList.size() - 2; i++)
            DFS(bar, i);

        System.out.println(res);
    }
    
    //벽을 세운다. (백트래킹)
    static void DFS(int num, int idx){
        if (num == 0) {
            int[][] cpyMap = new int[N][M];
            for (int i = 0 ; i < N; i++)
                for (int j = 0  ; j < M; j++)
                    cpyMap[i][j] = m[i][j];

            //바이러스 퍼뜨리기
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++){
                for (int j = 0 ; j < M; j++){
                    if (cpyMap[i][j] == 2 && !visited[i][j])
                        spread(visited, cpyMap, i, j);
                }
            }
            //안전 구역 갯수 세기
            res = Math.max(res, getSafetyZone(cpyMap));
            return ;
        }
        if (idx >= safeList.size()) return ;

        for (int i = idx; i < safeList.size(); i++) {
            int cx = safeList.get(i).x;
            int cy = safeList.get(i).y;

            m[cx][cy] = 1;
            DFS(num - 1, i + 1);
            m[cx][cy] = 0;
        }
    }
    
    
    // 바이러스를 퍼뜨린다. (BFS)
    static void spread(boolean[][] visited, int[][] cpyMap, int x, int y){
        visited[x][y] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y));

        while (!q.isEmpty()){
            int cx = q.peek().x , cy= q.peek().y; q.poll();

            for (int i = 0 ; i < 4; i++){
                int nx = cx + dx[i], ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (visited[nx][ny] || cpyMap[nx][ny] != 0)
                    continue;

                visited[nx][ny] = true;
                cpyMap[nx][ny] = 2;
                q.offer(new Pos(nx, ny));
            }
        }

    }

    // 맵의 모든 안전 영역을 구한다.
    static int getSafetyZone(int[][] m){
        int ans = 0;
        for (int i =0 ; i < N; i++)
            for (int j =0; j < M; j++)
                if (m[i][j] == 0) ans++;

        return ans;
    }
}