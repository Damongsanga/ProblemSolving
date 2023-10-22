import java.util.*;

class Solution {
    static class Truck{
        int idx; int inTime;
        Truck(int idx, int inTime){
            this.idx = idx;
            this.inTime = inTime;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int N = truck_weights.length;
        int idx = 0;
        int w = 0;
        int time = 1;
        Queue<Truck> queue = new ArrayDeque();
        while(idx < N){
            while (idx < N && w+truck_weights[idx] <= weight && queue.size() < bridge_length){
                w += truck_weights[idx];
                queue.add(new Truck(idx++, time++));
            } 
            while (idx < N && (w+truck_weights[idx] > weight || queue.size() == bridge_length)){
                Truck t = queue.poll();
                time = Math.max(t.inTime + bridge_length, time);
                w -= truck_weights[t.idx];
            } 
        }
        while(!queue.isEmpty()){
            time = queue.poll().inTime + bridge_length;
        }
        
        return time;
    }
}