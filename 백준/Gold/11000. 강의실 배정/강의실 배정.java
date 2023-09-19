import java.io.*;
import java.util.*;

class Node {
	int r;
	int c;

	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[][] arr = new Integer[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, (o1, o2) -> {
			return o1[0] - o2[0]; // 시작하는 순서가 빠른 순서대로
		});

//		System.out.println(Arrays.deepToString(arr));

		// 시작 정보도 가져야 하나?
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
		pq.add(arr[0][1]); // 초기값 설정, 종료시간 저장

		for (int i = 1; i < N; i++) {
			Integer[] now = arr[i];
			if (pq.peek() <= now[0]) { // 현재 제일 빠른 종료시간의 강의실이 새로운 강의 시작시간보다
				pq.poll(); // 그 강의실에 배정가능
				pq.add(now[1]);
			} else { // 아니라면
				pq.add(now[1]); // 해당 강의실에 배정
			}
		}

		System.out.println(pq.size());
	}

	/*
	 * 반례 6 0 1 1 2 1 4 0 3 3 4 2 6 3이 나와야 하나 4가 나옴
	 */
}