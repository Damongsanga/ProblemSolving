import java.util.*;

class Solution {
	static Set<Integer> set;
    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        Map<String, List<Integer>> map = new HashMap<>();
        
        int N = user_id.length;
        int M = banned_id.length;
        
        for (int i = 0; i < M; i++) {
        	String banned = banned_id[i];
        	map.putIfAbsent(banned, new ArrayList<>());
			for (int j = 0; j < N; j++) {
				String user = user_id[j];
				if (user.length() != banned.length()) continue;
				boolean flag = true;
				for (int k = 0; k < user.length(); k++) {
					if (banned.charAt(k) == '*') continue;
					if (user.charAt(k) != banned.charAt(k)) {
						flag = false;
						break;
					}
				}
				if (flag) map.get(banned).add(j);
			}
		}
        
        boolean[] visited = new boolean[(1<<N)];
        
        
        DFS(0, 0, N, M, user_id, banned_id, map, visited);
        
        return set.size();
    }
    
    static void DFS(int selected, int depth, int N, int M, String[] user_id, String[] banned_id, Map<String, List<Integer>> map, boolean[] visited) {
    	    	    	
    	if (depth == M) {
    		set.add(selected);
    		return;
    	}
    	
    	if (visited[selected]) return;
    	visited[selected] = true;
    	
    	for(Integer i : map.get(banned_id[depth])){
    		if (((1 << i) & selected) == 0) {
    			selected |= (1<<i);
    			DFS(selected, depth+1, N, M, user_id, banned_id, map, visited);
    			selected -= (1<<i);
    		}
    	}
    }
}