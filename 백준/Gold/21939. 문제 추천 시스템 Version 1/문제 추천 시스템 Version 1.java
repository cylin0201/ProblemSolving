import java.util.*;
import java.io.*;

class Main{
    static int N, P;
    static Map<Integer, Integer> map = new HashMap<>();

    static PriorityQueue<Problem> minHeap = new PriorityQueue<>((o1, o2) -> {
        if (o1.diff == o2.diff) return Integer.compare(o1.num, o2.num);
        return Integer.compare(o1.diff, o2.diff);
    });

    static PriorityQueue<Problem> maxHeap = new PriorityQueue<>((o1, o2) -> {
        if (o1.diff == o2.diff) return Integer.compare(o2.num, o1.num);
        return Integer.compare(o2.diff, o1.diff);
    });

    static class Problem{
        int num, diff;
        Problem(int num, int diff){
            this.num = num;
            this.diff = diff;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            map.put(key, value);
            minHeap.offer(new Problem(key, value));
            maxHeap.offer(new Problem(key, value));
        }

        P = Integer.parseInt(br.readLine());
        String cmd; int a, b;

        for (int i = 0 ; i < P; i++){
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();

            if (cmd.equals("add")){
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                map.put(a, b);
                minHeap.offer(new Problem(a, b));
                maxHeap.offer(new Problem(a, b));
            }
            else if (cmd.equals("recommend")){
                a = Integer.parseInt(st.nextToken());

                if (a == 1){
                    while (true){
                        Problem cur = maxHeap.peek();
                        if (map.containsKey(cur.num) && map.get(cur.num) == cur.diff){
                            System.out.println(cur.num);
                            break;
                        }
                        maxHeap.poll(); // 쓰레기 제거
                    }
                }
                else{
                    // minHeap
                    while (true){
                        Problem cur = minHeap.peek();
                        if (map.containsKey(cur.num) && map.get(cur.num) == cur.diff){
                            System.out.println(cur.num);
                            break;
                        }
                        minHeap.poll(); // 쓰레기 제거
                    }
                }
            }
            else{ // solved
                a = Integer.parseInt(st.nextToken());
                map.remove(a); // 핵심 변경
            }
        }
    }
}