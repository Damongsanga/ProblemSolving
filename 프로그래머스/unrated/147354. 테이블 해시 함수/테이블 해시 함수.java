import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
    	Arrays.sort(data, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[col-1] == o2[col-1]) {
					return o2[0] - o1[0];
				}
				return o1[col-1] - o2[col-1];
			}
        });
    
        
        int[] Si = new int[row_end-row_begin+1];
        
        for (int i = row_begin; i <= row_end; i++) {
        	int sum = 0;
        	for (int j = 0; j < data[0].length; j++) {
        		sum += data[i-1][j] % i;
        	}
        	Si[i-row_begin] = sum;
        }
        
        int answer = Si[0];
        for (int i = 1; i < Si.length; i++) {
        	answer = answer ^ Si[i];
        }
        

        return answer;
    }
}