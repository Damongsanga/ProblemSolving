class Solution {
    public int solution(String name) {
        
        char[] arr = name.toCharArray();
        int srt = 1; // 0은 탐색하지 않음
        int end = 1; // 0은 탐색하지 않음
        int rangeLeft = 0; // index 0을 제외한 A 연속 구간 왼쪽 길이
        int rangeRight = 0; // A 연속 구간 길이
        int rangeMiddle = 0; // A 연속 구간 오른쪽 길이
        int move = arr.length-1; // A를 고려하지 않고 좌우 이동하는 최대값 
        while(end < arr.length) {
        	// srt : a가 시작하는 index
        	// end : a가 끝나는 index
        	
        	// A만 포함하는 구간으로 end를 이동시킨다. 여기서 end가 이동하는 경우는 srt가 가리키는 값이 이미 A임으로 srt 이동은 고려할 필요가 없다.
        	while(end < arr.length && arr[end] == 'A') end++;
        	rangeLeft = srt-1; // 1 ~ srt-1 까지
        	rangeMiddle = end-srt+1; // srt ~ end
        	rangeRight = arr.length-end; // end ~ arr.length-1
        	move = Math.min(move, Math.min(rangeLeft, rangeRight) * 2 + Math.max(rangeLeft, rangeRight)); // 한쪽 구간 중 짧은 구간을 왕복하는 경우가 기존 move보다 작은 경우 갱신
        	
        	// A가 포함하지 않는 구간까지 end와 srt를 이동시킨다.
        	while(end < arr.length && arr[end] != 'A') end++;
        	srt = end;
        }

        
        for (int i = 0; i < arr.length; i++) {
			move += Math.min(arr[i]-'A', '[' - arr[i]);
		}
        
        return move;
    }
    public static void main(String[] args) {
		System.out.println(new Solution().solution("JERONE"));
	}
}