import java.util.*;
import java.io.*;

public class Main{
    static int n, m, k;
    static int[][] map;
    static int day = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Pair> q = new ArrayDeque<>();

        m = Integer.parseInt(st.nextToken()); n = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) q.offer(new Pair(i, j));
            }
        }
        while (!q.isEmpty()){
            int t = q.size();

            for (int i = 0; i < t; i++) {
                int cx = q.peek().x, cy = q.peek().y;
                q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j], ny = cy + dy[j];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                        continue;
                    if (map[nx][ny] != 0)
                        continue;

                    map[nx][ny] = 1;
                    q.offer(new Pair(nx, ny));
                }
            }
            day++;
        }

        int res = checkMap() ? day - 1 : -1;
        System.out.println(res);
    }


    static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x; this.y =y;
        }
    }

    static boolean checkMap(){
        for (int i = 0 ; i < n; i++){
            for (int j = 0; j < m; j++){
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }
}