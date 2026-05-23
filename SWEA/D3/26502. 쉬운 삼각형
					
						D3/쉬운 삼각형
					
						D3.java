import java.util.*;
import java.io.*;

class Solution{
    static int N;
    static Pos[] arr;
    static int res;
    static List<Pos> list = new ArrayList<>();

    static class Pos{
        int x, y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T --> 0) {
            res = 0;
            N = Integer.parseInt(br.readLine());
            arr = new Pos[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[i] = new Pos(x, y);
            }

            DFS(0, 0);

            System.out.println(res);
        }
    }

    static void DFS(int node, int n){
        if (n == 3){
            Pos p1 = list.get(0), p2 = list.get(1), p3 = list.get(2);
            if ((p1.x == p2.x && p2.y == p3.y) || (p2.x == p3.x && p1.y == p2.y))
                res = Math.max(res, getDistance(p1, p2) * getDistance(p2, p3));

            else if ((p1.x == p3.x && p3.y == p2.y) || (p2.x == p3.x && p1.y == p3.y))
                res = Math.max(res, getDistance(p1, p3) * getDistance(p2, p3));

            else if ((p1.x == p2.x && p1.y == p3.y) || (p1.x == p3.x && p1.y == p2.y))
                res = Math.max(res, getDistance(p1, p2) * getDistance(p1, p3));
            return ;
        }

        for (int i = node; i < arr.length; i++){
            list.add(arr[i]);
            DFS(i + 1, n + 1);
            list.remove(list.size() - 1);
        }

    }

    static int getDistance(Pos p1, Pos p2){
        if (p1.x == p2.x)
            return Math.abs(p1.y - p2.y);
        else
            return Math.abs(p1.x - p2.x);
    }
}