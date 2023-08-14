import java.util.List;

import javax.net.ssl.CertPathTrustManagerParameters;

import java.util.ArrayList;


class Solution {
	public static void main(String[] args) {
		solution(4,10);

}
	
    public static int[] solution(int n, long k) {

    	int[] answer = new int[n];
        ArrayList<Integer> arraylist = new ArrayList<Integer>(n);
        
        long l = 1;
        for (int tmp = 1; tmp <= n; tmp++) {
        	arraylist.add(tmp);
        	l *= tmp;
        }
        
        k--;
        int idx = 0;
        while(idx < n) {
            l /= n - idx;
            answer[idx++] = arraylist.remove((int) (k / l));
            k %= l;
        }
    	

    	
    	return answer;
    }
    
    
}