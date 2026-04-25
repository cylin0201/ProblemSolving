import java.util.*;
import java.io.*;

class Main{
    static int n, INF = (int)1e9;
    static Map<Character, Integer> m1 = new HashMap<>();
    static Map<Integer, Character> m2 = new HashMap<>();
    static int[][] arr = new int[52][52];
    static List<Node> res = new ArrayList<>();

    static class Node{
        int a, b;
        Node(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < 26; i++){
            m1.put((char)('A' + i), i);
            m2.put(i, (char)('A' + i));

        }
        for (int i = 26; i < 52; i++){
            m1.put((char)('a' + i - 26), i);
            m2.put(i, (char)('a' + i - 26));
        }

        for (int i = 0; i < 52; i++){
            Arrays.fill(arr[i], INF);
            arr[i][i] = 0;
        }

        for (int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " => ");
            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            arr[m1.get(a)][m1.get(b)] = 1;
        }

        for (int k = 0 ; k < 52; k++){
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 52; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                if (i == j) continue;
                if (arr[i][j] != INF) res.add(new Node(i, j));
            }
        }

        Collections.sort(res, (o1, o2) -> {
            if (o1.a == o2.a) return o1.b - o2.b;
            return o1.a - o2.a;
        });

        System.out.println(res.size());
        for (int i = 0 ; i < res.size(); i++){
            Node node = res.get(i);
            System.out.println(m2.get(node.a) + " => " + m2.get(node.b));
        }
    }
}