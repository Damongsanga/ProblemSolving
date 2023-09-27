import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] cArr = new int[N+1];
        cArr[0] = -1_000_000_001; // 첫 값을 최소 값보다 작은 값으로 설정해야 한다!!
        int[] indexOfLIS = new int[N]; // LIS 출력용 인덱스 저장 배열
        int idx = 0;
        for (int i = 0; i < N; i++) {

            // 1. 최대값보다 큰 경우 다음 idx에 추가
            // 새로운 값 갱신임으로 해당 값들은 LIS에 포함되는 값이라고 확정할 수 있을 것이다
            // 따라서 LIS로 출력하기 위해 해당 인덱스 (i)의 값을 저장한다
            if (arr[i] > cArr[idx]) {
                cArr[++idx] = arr[i];
                indexOfLIS[i] = idx;

                continue;
            }

            // 2. 중간 삽입이 필요한 경우 이분 탐색
            int l = 1;
            int r = idx;
            while (l < r){
                int mid = l + (r-l)/2;
                if (cArr[mid] >= arr[i]) r = mid;
                else l = mid+1;
            }
            cArr[l] = arr[i];
            indexOfLIS[i] = l;
            
            // 이 경우는 binarySearch 메소드를 사용하지 못하는데 이는 해당 메소드가 정확한 값을 찾아주기 떄문이다
//           cArr[Arrays.binarySearch(cArr, 1, idx+1, arr[i])] = arr[i];
        }
        
        Stack<Integer> stack = new Stack();
        
        int target = idx;
        for (int i = N-1; i >=0; i--) {
			if (indexOfLIS[i] == target) {
				stack.push(arr[i]);
				target--;
			}
		}

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
        	sb.append(stack.pop() + " ");
        }

        System.out.println(idx);
        System.out.println(sb);
    }
}