import java.util.*;

class Solution {
	
    public int solution(int[] stones, int k) {
        int l = 0;
        int r = 200_000_001;
        while(l < r) {
        	int mid = (l+r)/2; // 건넌 사람의 수 (현재 건널 수 있는 돌의 숫자)
        	if (isPossible(stones, mid, k)) {
        		l = mid+1;
        	} else {
        		r = mid;
        	}
        }
        
        return r;
    }
    static boolean isPossible(int[] stones, int mid, int k) {
    	int count = 0;
    	for (int i = 0; i < stones.length; i++) {
			if (stones[i] <= mid) {
				count++;
			} else {
				count = 0;
			}
			if (count >= k) {
				return false;
			}
		}
    	return true;
    }

}