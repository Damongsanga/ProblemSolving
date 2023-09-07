class Solution {
    public long solution(int n, int[] times) {
        long left = 1;
        long right = 1_000_000_000L * 1_000_000_000L;
        long mid;


        while(left < right){
            mid = (left + right) / 2;
            long sum = 0;
            for (long i: times) {
                sum += mid/i;
            }
            if (sum >= n) right = mid;
            else left = mid + 1;
        }

        return left;

    }

}