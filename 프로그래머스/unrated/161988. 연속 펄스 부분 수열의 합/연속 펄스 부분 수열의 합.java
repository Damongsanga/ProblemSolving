import java.util.Arrays;

class Solution {
    public long solution(int[] sequence) {
        int N = sequence.length;
        long[] prefixSum = new long[N];
        for (int i = 1; i < N; i+=2) {
            sequence[i] = -sequence[i];
        }


        prefixSum[0] = sequence[0];
        long max = prefixSum[0];
        long min = prefixSum[0];
        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i-1] + sequence[i];
            if (prefixSum[i] > max) max = prefixSum[i];
            if (prefixSum[i] < min) min = prefixSum[i];
        }


        return Math.max(Math.max(Math.abs(max-min), Math.abs(min-max)), Math.max(Math.abs(max), Math.abs(min)));
    }
}