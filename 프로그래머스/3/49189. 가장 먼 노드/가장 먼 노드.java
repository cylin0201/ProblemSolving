import java.util.*;

class Solution {
    static int INF = (int)1e9;
    static List<Edge>[] edges;
    static int[] dist;
    public int solution(int n, int[][] edge) {
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();
        
        for (int i = 0 ; i < edge.length; i++){
            edges[edge[i][0]].add(new Edge(edge[i][1], 1));
            edges[edge[i][1]].add(new Edge(edge[i][0], 1));
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        dijkstra(1);
        int maxVal = -1;
        for (int i = 2; i <= n; i++){
            maxVal = Math.max(maxVal, dist[i]);
            map.put(dist[i], map.getOrDefault(dist[i], 0) + 1);
        }
        
        return map.get(maxVal);
    }
    
    static void dijkstra(int start){
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                                        (o1, o2) -> Integer.compare(o1.d, o2.d));
        pq.offer(new Edge(start, 0));
        
        while (!pq.isEmpty()){
            int cur = pq.peek().to, distance = pq.peek().d; pq.poll();
            
            if (dist[cur] < distance) continue;
            
            for (Edge edge: edges[cur]){
                int next = edge.to, nextDistance = edge.d + distance;
                
                if (dist[next] > nextDistance){
                    dist[next] = nextDistance;
                    pq.offer(new Edge(next, nextDistance));
                }
            }
        }
    }
    
    static class Edge{
        int to, d;
        Edge(int to, int d){
            this.to = to;
            this.d = d;
        }
    }
}