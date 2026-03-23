import java.util.*;
import java.io.*;

class Main{
    static int N, K;
    static int[] dist = new int[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Pair> dq = new ArrayDeque<>();
        dist[N] = 0;
        dq.offer(new Pair(N, 0));

        while (!dq.isEmpty()){
            Pair peek = dq.peek(); dq.pollFirst();
            if (peek.x == K)
                continue;

            int nx, nt;
            for (int i = 0; i < 3; i++){
                if (i == 0){
                    nx = peek.x - 1;
                    nt = peek.t + 1;
                }
                else if (i == 1){
                    nx = peek.x + 1;
                    nt = peek.t + 1;
                }
                else{
                    nx = peek.x * 2;
                    nt = peek.t;
                }

                if (nx < 0 || nx > 100000)
                    continue;

                if (dist[nx] > nt){
                    dist[nx] = nt;

                    if (i <= 1) dq.offerLast(new Pair(nx, nt));
                    else dq.offerFirst(new Pair(nx, nt));
                }
            }
        }
        System.out.println(dist[K]);
    }

    static class Pair{
        int x, t;
        Pair(int x, int t){
            this.x = x;
            this.t = t;
        }
    }
}