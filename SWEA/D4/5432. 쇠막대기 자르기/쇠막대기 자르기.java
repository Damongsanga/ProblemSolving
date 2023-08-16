import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
			char[] input = sc.next().toCharArray();
			Stack<Character> stack = new Stack<>();
			int answer = 0;
			for (int i = 0; i < input.length; i++) {
				if (input[i] == '(' && input[i+1] == ')') {
					answer += stack.size();
					i++;
				} else if (input[i] == ')'){
					stack.pop();
					answer++;
				} else {
					stack.push('(');
				}
			}
			
			System.out.printf("#%d %d \n",test_case, answer);
		}
    }
}

