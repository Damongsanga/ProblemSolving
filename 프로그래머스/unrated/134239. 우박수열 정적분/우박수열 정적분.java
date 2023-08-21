import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	public double[] solution(int k, int[][] ranges) {

		// 우박수열 좌표 저장
		ArrayList<Double> list = new ArrayList<>();
		int len = 0; // 우박수열의 길이
		double ubak = k; // y값
		while (ubak > 1) {
			list.add( ubak );
			if (ubak % 2 == 0)
				ubak /= 2;
			else
				ubak = ubak * 3 + 1;
			len++;
		}
		list.add(ubak);
		
		
		// 정적분 구간 누적합
		double[] sum = new double[len];
		int idx = 0;
		sum[0] = (list.get(idx) + list.get(idx + 1)) / 2;
		idx++;
		for (int i = 1; i < len; i++) {
			sum[i] = sum[i - 1] + (list.get(idx) + list.get(idx + 1)) / 2;
			idx++;
		}

		// ranges에 대한 result 구하기
		// srt가 0이면 시작점부터임으로 누적합 배열에서는 고려대상이 아님
		double[] answer = new double[ranges.length];
		for (int i = 0; i < ranges.length; i++) {
			int srt = ranges[i][0];
			int end = ranges[i][1];
			if (srt - end > len) {
				answer[i] = -1;
				continue;
			}
			if (srt == 0) {
				if (end == -len) answer[i] = 0.0;
				else answer[i] = sum[len - 1 + end];
			} else {
				answer[i] = sum[len - 1 + end] - sum[srt - 1];
			}
//			answer[i] = (srt == 0 ? sum[len - 1 + end] : sum[len - 1 + end] - sum[srt - 1]);
		}
		return answer;
	}

}