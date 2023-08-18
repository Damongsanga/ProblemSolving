import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = {0,1,2,1,2,1,2,3,2,3,2,3,4,3,4};
		int answer = -1;
		if (N != 4 && N != 7) {
			answer = N/15 * 3 + arr[N%15];
		}
		System.out.println(answer);
		
		
	}


}