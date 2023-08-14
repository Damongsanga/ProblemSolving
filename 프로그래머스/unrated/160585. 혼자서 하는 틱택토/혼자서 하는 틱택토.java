class Solution {
    public int solution(String[] board) {
    	int count_O = 0;
    	int count_X = 0;
    	int answer = 1;
    	for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i].toCharArray()[j] == 'O') count_O++;
				else if (board[i].toCharArray()[j] == 'X') count_X++;
			}
		}
    	
    	if (count_X > count_O || count_O - count_X >=2) {
    		answer = 0;
//    		System.out.println("개수 미스매치");
    	}
    	else if (bingo('O', board) > 0 && bingo('X', board) > 0) {
    		answer = 0;
//    		System.out.println("둘다 승리");
    	}
    	else if (bingo('O', board) > 0 && count_O - count_X != 1) {
    		answer = 0;
//    		System.out.println("O 승리 후 조건 미성립");
    	}
    	else if (bingo('X', board) > 0 && count_O != count_X ) {
    		answer = 0;
//    		System.out.println("X 승리 후 조건 미성립");
    	}
    	else {
//    		System.out.println("이외 조건으로 성립");
    	}
    		
    	return answer;
    	
    	
    }
    public static int bingo(char x, String[] board) {
    	int count = 0;
    	int diagonal_r = 0;
		int diagonal_l = 0;
		int row = 0;
		int col = 0;
    	for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i].toCharArray()[j] == x) row++; // 가로
				if (board[j].toCharArray()[i] == x) col++; // 세로
			}
			if (row == 3) count++; // 가로
			if (col == 3) count++;
			if (board[i].toCharArray()[i] == x) diagonal_r++; // 대각선
			if (board[i].toCharArray()[2-i] == x) diagonal_l++; //대각선
			row = 0; col = 0; // 초기화
		}
    	if (diagonal_l == 3) count++;
    	if (diagonal_r == 3) count++;
    
    	return count;
    }
    
}