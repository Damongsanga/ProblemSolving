import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// '*' 가 42, '+' 가 43, '-'가 45, '/'가 47
public class Main {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        long leftOct = 0;
        long rightOct = 0;
        char operator = '+';
        for (int i = 1; i < input.length(); i++) { // 첫 - 무시하기 위함
            if ('*' <= input.charAt(i) && input.charAt(i) <= '/'){
                leftOct = input.charAt(0) == '-'? -Long.parseLong(input.substring(1,i), 8) : Long.parseLong(input.substring(0, i), 8);
                operator = input.charAt(i);
                rightOct = input.charAt(i+1) == '-'? -Long.parseLong(input.substring(i+2), 8) : Long.parseLong(input.substring(i+1), 8);
                break;
            }
        }

        if (operator == '/' && rightOct == 0) {
            System.out.println("invalid");
            return;
        }
        System.out.println(toOct(operate(leftOct, rightOct, operator)));

    }

    private static long operate(long leftOct, long rightOct, char operator) {
        switch (operator){
            case '+' : return leftOct + rightOct;
            case '-' : return leftOct - rightOct;
            case '*' : return leftOct * rightOct;
            case '/' :
                if (leftOct * rightOct < 0 && leftOct % rightOct != 0) return leftOct / rightOct - 1;
                return leftOct / rightOct;
            default:
                return 0;
        }
    }

    private static String toOct(long input){
        return input < 0 ? "-" + Long.toOctalString(-input) : Long.toOctalString(input);
    }
}