import java.util.*;
import java.io.*;

class Main{
    static int n, k;
    static int[] arr, parent;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) break;

            arr = new int[n];
            parent = new int[n];          // index 기반
            tree = new ArrayList[n];

            for (int i = 0; i < n; i++)
                tree[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            int pIdx = -1;  // 부모 index
            int kIdx = -1;

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

                if (arr[i] == k) kIdx = i;

                if (i == 0) {
                    parent[i] = -1; // root
                    continue;
                }

                if (arr[i] - arr[i - 1] != 1) {
                    pIdx++;
                }

                parent[i] = pIdx;
                tree[pIdx].add(i);
            }

            // k가 없는 경우
            if (kIdx == -1) {
                System.out.println(0);
                continue;
            }

            int kParent = parent[kIdx];
            if (kParent == -1) {
                System.out.println(0);
                continue;
            }

            int kGrand = parent[kParent];
            if (kGrand == -1) {
                System.out.println(0);
                continue;
            }

            int res = 0;

            for (int siblingParent : tree[kGrand]) {
                if (siblingParent == kParent) continue;
                res += tree[siblingParent].size();
            }

            System.out.println(res);
        }
    }
}