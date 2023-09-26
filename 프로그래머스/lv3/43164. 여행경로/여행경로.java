import java.util.*;

class Solution {
	
	static Map<String, ArrayList<String>> map;
	static String[] answer;
	
    public String[] solution(String[][] tickets) {
    	int T = tickets.length;
        answer = new String[T+1];
        
        // 1. 정렬 (시작 > 도착)
        Arrays.sort(tickets, (o1, o2) -> 
        	o1[0].equals(o2[0]) ? o1[1].compareTo(o2[1]) : o1[0].compareTo(o2[0])
        );
        
        map = new HashMap();
        
        for(String[] ticket : tickets) {
        	String from = ticket[0];
        	String to = ticket[1];
        	if (!map.containsKey(from)) map.put(from, new ArrayList<>());
        	if (!map.containsKey(to)) map.put(to, new ArrayList<>());
        	map.get(from).add(to);
        }

        answer[0] = "ICN";
        DFS(0, "ICN", new String[T], T);

        return answer;
    }
    
    static boolean DFS(int depth, String now, String[] path, int T) {    	
    	if (depth == T) {
    		for (int i = 1; i <= T; i++) {
				answer[i] = path[i-1];
			}
    		return true;
    	}
    	
    	ArrayList<String> nextDestination = map.get(now);
    	for (int i = 0; i < nextDestination.size(); i++) {
    		String next = nextDestination.get(i);
			if (next.equals("")) continue;
			nextDestination.set(i, "");
			path[depth] = next;
			if (DFS(depth+1, next, path, T)) return true;
			nextDestination.set(i, next);
		}
    	
    	return false;
    	
    }
}