import java.util.Arrays;

class Solution {

    static class Node implements Comparable<Node>{
        int food; int idx;

        public Node(int food, int idx) {
            this.food = food;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            if (this.food == o.food) return this.idx - o.idx;
            return this.food - o.food;
        }
    }

    public int solution(int[] food_times, long k) {
        int N = food_times.length;

        Node[] nodes = new Node[N];
        for (int idx = 0; idx < N; idx++) {
            nodes[idx] = new Node(food_times[idx], idx);
        }

        Arrays.sort(nodes);

        int count = 0; // 다 먹은 음식 갯수 (idx 아님)
        while(count < N){
            long next = (long) (N - count) * (nodes[count].food - (count == 0? 0 : nodes[count-1].food));
            if (k < next) break; // 남은 음식의 최소값만큼 모두 먹지 못하면, break;
            k -= next;
            count++;
        }
        if (count == N) return -1;
        
        k %= (N-count);

        if (count == 0){
            return (int) k + 1;
        }

        for (int i = 0; i < N; i++) {
            if (food_times[i] <= nodes[count-1].food) continue; // 현재 다 먹은 음식의 갯수보다 많은 경우는 제외
            if (k-- == 0) {
                return i+1;
            }
        }

        return -1;
    }
}