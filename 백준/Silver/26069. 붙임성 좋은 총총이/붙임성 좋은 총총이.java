import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name1 = st.nextToken();
            String name2 = st.nextToken();
            if (name2.equals("ChongChong") || name1.equals("ChongChong")){
                set.add(name1);
                set.add(name2);
            }
            if (set.contains(name1) || set.contains(name2)){
                set.add(name1);
                set.add(name2);
            }
        }
        System.out.print(set.size());

    }
}
