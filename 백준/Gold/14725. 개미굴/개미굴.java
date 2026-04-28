import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static class TrieNode {
        TreeMap<String, TrieNode> childNodes = new TreeMap<>();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        TrieNode root = new TrieNode();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            TrieNode current = root; // 각 개미의 시작은 항상 루트부터

            for (int j = 0; j < k; j++) {
                String food = st.nextToken();

                // 현재 방의 하위 방 중에 food가 없다면 새로 만들고(putIfAbsent)
                current.childNodes.putIfAbsent(food, new TrieNode());

                // 해당 하위 방으로 이동(폴더 안으로 들어가기)
                current = current.childNodes.get(food);
            }
        }

        printTrie(root, "");
    }

    // DFS (깊이 우선 탐색) 방식으로 트리를 순회하며 출력하는 메서드
    static void printTrie(TrieNode node, String prefix) {
        for (String key : node.childNodes.keySet()) {
            // 현재 층의 방 이름 출력 (앞에 prefix 포함)
            System.out.println(prefix + key);

            // 다음 층(자식 노드)으로 들어갈 때는 prefix에 "--"를 추가하여 재귀 호출
            printTrie(node.childNodes.get(key), prefix + "--");
        }
    }
}