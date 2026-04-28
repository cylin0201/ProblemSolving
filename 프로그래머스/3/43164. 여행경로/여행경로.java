import java.util.*;

class Solution {
    boolean[] visited;
    List<String> allRoutes;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        allRoutes = new ArrayList<>();
        
        dfs("ICN", "ICN", tickets, 0);        
        Collections.sort(allRoutes);
        
        return allRoutes.get(0).split(" ");
    }
    
    void dfs(String node, String route, String[][] tickets, int count) {
        if (count == tickets.length) {
            allRoutes.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(node) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, count + 1);
                visited[i] = false;
            }
        }
    }
}