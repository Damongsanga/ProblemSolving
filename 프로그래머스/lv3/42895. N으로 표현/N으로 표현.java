import java.util.*;

class Solution {
    public int solution(int N, int number) {
    	Set<Integer>[] arr = new Set[9];
    	
    	for (int i = 0; i < 9; i++) {
			arr[i] = new HashSet<Integer>();
 		}
    	
    	arr[1].add(N);
    	String tmp = N + "";
    	for (int i = 2; i < 9; i++) {
    		Set<Integer> nowSet = arr[i];
			for (int j = 1; j < i; j++) {
				Set<Integer> leftSet = arr[j];
				Set<Integer> rightSet = arr[i-j];
				
				for(int l:leftSet) {
					for(int r:rightSet) {
						nowSet.add(l + r);
						nowSet.add(l - r);
						nowSet.add(l * r);
						if (l != 0 && r != 0)
							nowSet.add(l / r);
					}
				}
				
			}
			tmp += N;
			nowSet.add(Integer.parseInt(tmp));
		}
    	
    	for (int i = 1; i < 9; i++) {
    		if (arr[i].contains(number)) {
    			return i;
    		}
    	}

        return -1;
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().solution(5, 12));
		System.out.println(new Solution().solution(2, 11));
		System.out.println(new Solution().solution(1, 12));
	}
}