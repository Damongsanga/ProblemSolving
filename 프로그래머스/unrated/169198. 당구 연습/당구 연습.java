class Solution {
	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for (int i = 0; i < balls.length; i++) {
			int x; int y; int min = Integer.MAX_VALUE;
			for (int j = 0; j < 4; j++) {
				x = balls[i][0];
				y = balls[i][1];
				switch (j) {
				case 0:
					if (startY == y && startX < x) continue;
					x = 2*m - x;
					break;
				case 1:
					if (startY == y && startX > x) continue;
					x = -x;
					break;
				case 2:
					if (startX == x && startY < y) continue;
					y = 2*n -y;
					break;
				case 3:
					if (startX == x && startY > y) continue;
					y = -y;
				}
				min = Math.min(min, (int) (Math.pow(x-startX,2) + Math.pow(y-startY, 2)));
			
			}
			answer[i] = min;

		}

		return answer;
	}
	public static void main(String[] args) {
		new Solution().solution(10, 10, 3, 7, new int[][] {{7,7}, {2,7}, {7,3}});
	}
}