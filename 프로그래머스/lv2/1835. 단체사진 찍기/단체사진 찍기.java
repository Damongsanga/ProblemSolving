import java.util.*;

class Solution {
	static int answer;
	static Map<Character, Integer> map;
	public static void main(String[] args) {
		System.out.println(new Solution().solution(2, new String[] {"N~F=0", "R~T>2"}));
		System.out.println(new Solution().solution(2, new String[] {"M~C<2", "C~M>1"}));
	}
    public int solution(int n, String[] data) {
    	answer = 0;
    	map = new HashMap<>();
    	map.put('A', 0);
    	map.put('C', 1);
    	map.put('F', 2);
    	map.put('J', 3);
    	map.put('M', 4);
    	map.put('N', 5);
    	map.put('R', 6);
    	map.put('T', 7);
    	
    	permutation(new int[8], new boolean[8], 0, data);
    	
        return answer;
    }
    
    static void permutation(int[] arr, boolean[] visited, int depth, String[] data) {
    	if (depth == 8) {
    		if (able(arr, data)) answer++; 
    		return;
    	}
    	
    	for (int i = 0; i < 8; i++) {
    		if (visited[i]) continue;
    		visited[i] = true;
    		arr[depth] = i;
    		permutation(arr, visited, depth + 1, data);
    		arr[depth] = 0;
    		visited[i] = false;
    	}
    	
    	
    }
    
    static boolean able(int[] arr, String[] data) {
    	for (int i = 0; i < data.length; i++) {
    		char[] tmp = data[i].toCharArray();
    		int a = map.get(tmp[0]);
    		int b = map.get(tmp[2]);
    		int dist = tmp[4] - '0' + 1;
    		
    		switch(tmp[3]) {
    		case ('='):
    			if (Math.abs(arr[a] - arr[b]) != dist) {
    				return false;
    			}
    			break;
    		case ('>'):
    			if (Math.abs(arr[a] - arr[b]) <= dist) {
    				return false;
    			}
    			break;
    		case ('<'):
    			if (Math.abs(arr[a] - arr[b]) >= dist) {
    				return false;
    			}
    		}
    	}
    	return true;
    	
    }
   
}