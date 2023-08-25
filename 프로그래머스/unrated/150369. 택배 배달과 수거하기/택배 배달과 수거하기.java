import java.util.Arrays;

class Solution {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		// 주의, deliveries & pickups는 index 1부터 시작함 (상관은 없을듯)
		long answer = 0;
		int idx_d = n-1;
		int idx_p = n-1;
		
		// 초기화
		while(idx_d >= 0 && deliveries[idx_d] == 0) {
			idx_d--;
		}
		while(idx_p >= 0 && pickups[idx_p] == 0) {
			idx_p--;
		}
		
		
		while (idx_d >= 0 || idx_p >= 0) {
			
			answer += (Math.max(idx_d, idx_p)+1) * 2;
			
			
			int cap_d = cap;
			
			while(cap_d > 0 && idx_d >= 0) {
				if (deliveries[idx_d] <= cap_d) {
					cap_d -= deliveries[idx_d];
					deliveries[idx_d--] = 0;
				} else {
					deliveries[idx_d] -= cap_d;
					cap_d = 0;
				}
				
				while(idx_d >= 0 && deliveries[idx_d] == 0) {
					idx_d--;
				}
			}
			
			
			int cap_p = cap;
			
			while(cap_p > 0 && idx_p >= 0) {
				if (pickups[idx_p] <= cap_p) {
					cap_p -= pickups[idx_p];
					pickups[idx_p--] = 0;
				} else {
					pickups[idx_p] -= cap_p;
					cap_p = 0;
				}
				
				while(idx_p >= 0 && pickups[idx_p] == 0) {
					idx_p--;
				}
			}
			
//			System.out.println(Arrays.toString(deliveries));
//			System.out.println(Arrays.toString(pickups));
//			System.out.println("answer : " + answer);
//			System.out.println();
			
		}
		

		return answer;
	}

}
