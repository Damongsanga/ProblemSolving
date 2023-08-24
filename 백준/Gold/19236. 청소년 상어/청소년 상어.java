import java.util.Scanner;

class Node {
	int val;
	int dir;

	Node(int val, int dir) {
		this.val = val;
		this.dir = dir;
	}
}

public class Main {

	static int[] rr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] rc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int answer = 0;

	static Node[][] move(Node[][] arr) {
		
		Node[][] output = new Node[4][4];
		
		// 깊은 복사!! 여기서 틀려서 엄청 헤멤..
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] != null)
					output[i][j] = new Node(arr[i][j].val, arr[i][j].dir);
			}
		}
		// 물고기 16개 탐색
		for (int i = 1; i <= 16; i++) {
			
			// 해당 물고기를 찾기
			find : for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					Node fish = output[a][b]; // 이동할 물고기
					if (fish == null) continue;
					
					if (fish.val == i) {// 물고기를 찾았다면 이동
						for (int j = 0; j < 8; j++) {
							int nextDir = (fish.dir + j) % 8;
							int r = a + rr[nextDir];
							int c = b + rc[nextDir];
							if (r < 0 || c < 0 || r >= 4 || c >= 4) {
								continue;
							}
							if (output[r][c] != null && output[r][c].val == -1) continue; // 상어일 경우 스킵
							
							// 물고기 교환, 해당 Node의 위치정보도 갱신 필요
							// 해당 물고기 방향도 바꿔야함!

							output[a][b] = output[r][c];
							output[r][c] = fish;
							output[r][c].dir = nextDir;
								
							break;
						}
						break find;
					}
				}
			}
		}		
		return output;
	}

	static void backtracking(Node shark, int rShark, int cShark, int eaten, Node[][] arr) {

		int dirShark = shark.dir;
		arr[rShark][cShark] = null; // 현재 위치는 물고기가 없음 (상어가 먹었음으로)
		
		for (int i = 1; i <= 3; i++) { // 거리상 최대 3회 탐색 가능
			rShark += rr[dirShark];
			cShark += rc[dirShark];
			if (rShark < 0 || rShark >= 4 || cShark < 0 || cShark >= 4) continue; // 범위 바깥이면 스킵
			
			Node fish = arr[rShark][cShark];
			if (fish == null) continue; // 물고기가 없으면 스킵
			
			arr[rShark][cShark] = shark; // 물고기 먹고 이동
			shark.dir = fish.dir; // 상어 방향 변경
			backtracking(shark, rShark, cShark, eaten + fish.val, move(arr)); // 물고기가 이동한 후 추가 탐색
			arr[rShark][cShark] = fish; // 물고기 원복
			shark.dir = dirShark; // 상어 방향 원복
		}
		answer = Math.max(answer, eaten);
	}

	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Node[][] arr = new Node[4][4]; // 물고기 위치
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				arr[i][j] = new Node(a, b - 1); // 1~8 대신 0~7로 이동
			}
		}

		// 초기값 세팅
		int eaten_srt = arr[0][0].val;
		Node shark = new Node(-1, arr[0][0].dir); // 상어는 val이 -1
		arr[0][0] = shark; // 해당 위치 삭제
		
		backtracking(shark, 0,0, eaten_srt, move(arr));

		System.out.println(answer);
	}

}