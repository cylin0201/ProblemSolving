import java.util.*;
import java.io.*;

public class Main{
    static int t;
    static int n, m, k;
    static int[][] map;
    static int cnt;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        StringBuilder res = new StringBuilder();

        for (int i = 0 ; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            cnt = 0;

            map = new int[n][m];

            for (int j = 0 ; j < k; j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            for (int tx = 0; tx < n; tx++){
                for (int ty = 0 ; ty < m; ty++){
                    if (map[tx][ty] == 1) {
                        cnt++;
                        DFS(tx, ty);
                    }
                }
            }
            res.append(cnt).append('\n');
        }
        System.out.print(res);
    }

    static void DFS(int x, int y){
        if (map[x][y] != 1) return ;
        map[x][y] = 0;

        for (int i = 0 ; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;
            if (map[nx][ny] != 1)
                continue;

            DFS(nx, ny);
        }
    }
}