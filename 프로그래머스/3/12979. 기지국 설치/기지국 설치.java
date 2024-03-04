class Solution {
    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverage = 2 * w + 1;
        
        int idx = 1;
        int s = 0;
        while(s < stations.length && idx <= n){
            int l = stations[s] - w <= 1 ? 1 : stations[s] - w;
            int r = stations[s] + w >= n ? n : stations[s] + w;
            // System.out.println("l: " + l);
            // System.out.println("r: " + r);
            if (idx < l) answer += add(idx, l, coverage);
            while(s < stations.length-1 && stations[s+1] - w <= r) s++;
            idx = stations[s] + w + 1;
            s++;
        }
        
        if (idx <= n && idx > stations[s-1] - w){
            answer += add(idx, n+1, coverage);
        }

        return answer;
    }
    private static int add(int idx, int l, int coverage){
        return (l - idx - 1) / coverage + 1;
    }
}