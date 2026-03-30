import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static int res = (int)1e9;
    static int temp;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        
        for (int idx = 0; idx < wires.length; idx++){
            visited = new boolean[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++)
                graph[i] = new ArrayList<>();        
            
            
            for (int i = 0 ; i <wires.length; i++){
                if (i == idx) continue;
                graph[wires[i][0]].add(wires[i][1]);
                graph[wires[i][1]].add(wires[i][0]);
            }
            
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i<= n; i++){
                temp = 0;
                if (!visited[i]) DFS(i);
                
                if (temp > 0) list.add(temp);
            }
            res = Math.min(res, Math.abs(list.get(0) - list.get(1)));
        }
        
        return res;
    }
    
    void DFS(int node){
        if (visited[node]) return ;
        visited[node] = true;
        temp++;
        for (int next: graph[node])
            DFS(next);
    }
}