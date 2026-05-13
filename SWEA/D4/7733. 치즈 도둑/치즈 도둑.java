import java.io.*;
import java.util.*;

class Solution {
    static int N, T;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1 ,1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++){
            int res = 0;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int day = 0; day <= 100; day++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == day)
                            map[i][j] = 0;      //먹은 치즈는 0으로
                    }
                }
                res = Math.max(res, getCnt());
            }
            System.out.println("#" + t + " " + res);
        }
    }

    static int getCnt(){
        int cnt = 0;
        visited = new boolean[N][N];

        for (int i = 0 ; i < N; i++){
            for (int j = 0 ; j < N; j++){
                if (visited[i][j] || map[i][j] == 0)
                    continue;
                DFS(i, j);
                cnt++;
            }
        }
        return cnt;
    }

    static void DFS(int x, int y){
        visited[x][y] = true;
        for (int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] == 0) continue;
            DFS(nx, ny);
        }
    }
}