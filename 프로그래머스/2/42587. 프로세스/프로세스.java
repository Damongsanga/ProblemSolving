import java.util.*;
class Solution {
    static class Process{
        int p; int loc;
        Process(int p, int loc){
            this.p = p;
            this.loc = loc;
        }
        
    }
    public int solution(int[] priorities, int location) {
        int N = priorities.length;
        Queue<Process> queue = new ArrayDeque();
        for (int i = 0; i < N; i++){
            queue.add(new Process(priorities[i], i));
        }
        Integer[] parr = Arrays.stream(priorities).boxed().toArray(Integer[]::new);
        Arrays.sort(parr, Collections.reverseOrder());
        
        int answer = 0;
        while(!queue.isEmpty()){
            Process now = queue.poll();
            if (now.p == parr[answer]){
                answer++;
                if (now.loc == location) break;
            } else {
                queue.add(now);
            }
            
        }
        
        return answer;
    }
}