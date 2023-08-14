import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	public class Room{
		int srtTime;
		int endTime;
		Room(int srtTime, int endTime){
			this.srtTime = srtTime;
			this.endTime = endTime;
		}
	}
	
    public int solution(String[][] book_time) {
        int answer = 0;
        Room[] arr = new Room[book_time.length];
        
        for (int i = 0; i < book_time.length; i++) {
			arr[i] = new Room(changeTime(book_time[i][0]), changeTime(book_time[i][1]) + 10);
		}
        // 방 시작 순으로 정렬
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.srtTime, o2.srtTime));
        
        // 우선순위 큐 생성
        PriorityQueue<Room> pq = new PriorityQueue<Room>( (o1, o2)-> Integer.compare(o1.endTime, o2.endTime));
        
        for (int i = 0; i < arr.length; i++) {
			Room current = arr[i];
			if (pq.isEmpty()) {
				// pq 비어있다면 current 추가
				pq.add(current);
			} else {
				// pq가 비어있지 않다면 current 시작 시간보다 빨리 끝나는 Room은 모두 제거
				while(!pq.isEmpty() && current.srtTime >= pq.peek().endTime) {
					pq.poll();
				}
				// pq에 current 추가
				pq.add(current);
			}
			// 최고 방 개수로 답 갱신
			answer = Math.max(answer, pq.size());
		} 
        
        
        return answer;
    }
    
    public int changeTime(String s) {
    	return Integer.parseInt(s.split(":")[0]) * 60 + Integer.parseInt(s.split(":")[1]);
    }
    
}