import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int res;
    static int n, m;
    
    public int solution(int[][] maps) {
        n = maps.length; m = maps[0].length;
        visited = new boolean[n][m];
        
        BFS(maps, 0, 0);
        
        return res;
    }

    static void BFS(int[][]maps, int x, int y){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y, 1));

        while (!q.isEmpty()){
            int cx = q.peek().x, cy = q.peek().y, cd = q.peek().d;
            q.poll();
            
            if (cx == n - 1 && cy == m - 1){
                res = cd;
                return ;
            }
            
            for (int i = 0 ; i < 4; i++){
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (visited[nx][ny] || maps[nx][ny] != 1)
                    continue;
                
                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny, cd + 1));                
            }
        }
        res = -1;
    }
    
    static class Pos{
        int x, y, d;
        Pos(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}