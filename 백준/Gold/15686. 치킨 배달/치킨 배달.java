import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static List<Pos> chickens = new ArrayList<>();
    static List<Pos> homes = new ArrayList<>();
    static List<Pos> temp = new ArrayList<>();
    static int res = (int)1e9;

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

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N; j++){
                int input = Integer.parseInt(st.nextToken());
                if (input == 1){
                    homes.add(new Pos(i, j));
                }
                else if (input == 2){
                    chickens.add(new Pos(i, j));
                }
            }
        }
        DFS(-1, 0);

        System.out.println(res);
    }

    static void DFS(int idx, int cnt){
        if (cnt == M){
            res = Math.min(res, getDistance());
            return ;
        }

        for (int i = idx + 1; i < chickens.size() - M + cnt + 1; i++){
            temp.add(chickens.get(i));
            DFS(i, cnt + 1);
            temp.remove(temp.size() - 1);
        }
    }

    static int getDistance(){
        int res = 0;
        for (int i = 0 ; i < homes.size(); i++){
            int t = (int)1e9;
            Pos home = homes.get(i);
            for (int j = 0 ; j < temp.size(); j++){
                Pos chicken = temp.get(j);
                t = Math.min(t, Math.abs(chicken.x - home.x) + Math.abs(chicken.y - home.y));
            }
            res += t;
        }
        return res;
    }
}