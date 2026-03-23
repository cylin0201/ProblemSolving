import java.util.*;
import java.io.*;

public class Main{
    static int[] dx = {-1, 1};
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boolean[] visited= new boolean[100001];
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(N, 0));
        visited[N] = true;

        while (!q.isEmpty()){
            Pair peek = q.peek(); q.poll();
            if (peek.x == K){
                System.out.println(peek.t);
                return ;
            }

            for (int i = 0 ; i < 3; i++){
                int nx;
                if (i <= 1) nx = peek.x + dx[i];
                else nx = peek.x * 2;

                if (nx < 0 || nx > 100000)
                    continue;
                if (visited[nx])
                    continue;

                q.offer(new Pair(nx, peek.t + 1));
                visited[nx] = true;
            }
        }
    }

    static class Pair{
        int x, t;
        Pair(int x, int t){
            this.x = x;
            this.t = t;
        }
    }
}