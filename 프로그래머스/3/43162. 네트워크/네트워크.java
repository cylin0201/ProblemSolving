import java.util.*;

class Solution {
    static boolean[] visited;
    static List<Integer>[] graph;
    static int cnt = 0;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        graph = new ArrayList[n];
        for (int i = 0 ; i < n; i++)
            graph[i] = new ArrayList<>();
        
        for (int i = 0 ; i < n; i++){
            for (int j = 0 ; j < n; j++){
                if (i != j && computers[i][j] == 1){
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        for (int i = 0 ; i < n; i++){
            if (DFS(i)) cnt++;
        }
        
        return cnt;
    }
    
    static boolean DFS(int n){
        if (visited[n]) return false;
        visited[n] = true;
        
        for (int next: graph[n]){
            if (visited[next]) continue;
            DFS(next);
        }
        return true;
    }
}