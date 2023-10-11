import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Solution {
	static HashMap<String, List<Integer>> map;
	static String[][] candidate;
	
    public int[] solution(String[] info, String[] query) {
    	int N = info.length;
    	int M = query.length;
        int[] answer = new int[M];
        map = new HashMap<String, List<Integer>>();
        candidate = new String[4][4];
        candidate[0] = new String[] {"cpp", "java", "python", "-"};
        candidate[1] = new String[] {"backend", "frontend", "-", ""};
        candidate[2] = new String[] {"junior", "senior", "-", ""};
        candidate[3] = new String[] {"chicken", "pizza", "-", ""};
        
        combination(0, new String[4]);
        
        for (String str : info) {
        	String[] tmp = str.split(" ");
        	StringBuilder sb;
        	for (int i = 0; i < (1 << 4); i++) {
        		sb = new StringBuilder();
        		for (int j = 0; j < 4; j++) {
					if ((i & (1 << j)) > 0) sb.append(tmp[j]);
					else sb.append("-");
					if (j != 3) sb.append(" and ");
				}
        		map.get(sb.toString()).add(Integer.parseInt(tmp[4]));
        	}
        }
        
        for (List list : map.values()) {
        	Collections.sort(list);
        }
        
        for (int i = 0; i < M; i++) {
        	String[] tmp = query[i].split(" ");
        	String key = query[i].substring(0, query[i].length()-(tmp[7].length()+1));
        	List<Integer> list = map.get(key);
			answer[i] = list.size() - find(Integer.parseInt(tmp[7]), list);
		}
        
        return answer;
        
    }
    
    static void combination(int depth, String[] container) {
    	if (depth == 4) {
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < 4; i++) {
				sb.append(container[i]);
				if (i != 3) sb.append(" and ");
			}
    		map.put(sb.toString(), new ArrayList());
    		return;
    	}
    	for (int i = 0; i < 4; i++) {
			if (i == 3 && depth != 0) continue;
			container[depth] = candidate[depth][i];
			combination(depth+1, container);
		}
    }
    
    
    
    static int find(int target, List<Integer> list) {
    	int l = 0;
    	int r = list.size();
    	while(l < r) {
    		int mid = (l+r)/2;
    		if (list.get(mid) >= target) {
    			r = mid;
    		} else {
    			l = mid+1;
    		}
    	}
    	return r;
    }
    
    
}