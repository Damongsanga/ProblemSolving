import java.util.*;

class Solution {
    static int N;
    public long solution(int[] a, int[][] edges) {
        if (!isPossible(a)) return -1;

        N = a.length;
        
        long[] arr = Arrays.stream(a).asLongStream().toArray();

        int[] inV = new int[N];
        List<Integer>[] edgeLists = new List[N];

        for (int i = 0; i < N; i++) {
            edgeLists[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            inV[edge[0]]++;
            inV[edge[1]]++;
            edgeLists[edge[0]].add(edge[1]);
            edgeLists[edge[1]].add(edge[0]);
        }

        return topologicalSort(arr, edgeLists, inV);
    }

    private static boolean isPossible(int[] a){
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        return sum == 0;
    }

    private static long topologicalSort(long[] arr, List<Integer>[] edgeLists, int[] inV){
        long res = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (inV[i] == 1) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            inV[now]--;

            for (Integer next : edgeLists[now]) {
                if (inV[next] == 0) continue;

                res += Math.abs(arr[now]);
                arr[next] += arr[now];
                arr[now] = 0;


                inV[next]--;
                if (inV[next] == 1) {
                    queue.add(next);
                }
            }
        }

        return res;
    }
}