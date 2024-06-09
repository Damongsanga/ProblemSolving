import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 구간 합 구하기 2
 *  알고리즘 : 세그먼트 트리
 *  주요 포인트 :
 *  1. Lazy Propagation을 사용하여 업데이트 쿼리 처리
 *  2. 구현법에 집중
 *  3. 값을 바꾸는게 아닌 추가하는 것임으로 구현 방법에 신경써서 pushDown을 구현
 * */
public class Main {

    static long[] arr;
    static long[] tree;
    static long[] lazyValue;
    static boolean[] lazyExist;
    static int N;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new long[N+1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // tree에는 구간합이 저장됨
        tree = new long[4*N];
        lazyValue = new long[4*N];
        lazyExist = new boolean[4*N];
        initRec(1, N, 1);

        for (int i = 0; i < K + M; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            if (flag == 1){
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long addValue = Long.parseLong(st.nextToken());
                update(1, N, addValue, 1, left, right);
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(query(1, N, 1, left, right)).append("\n");
            }

        }

        System.out.println(sb);

    }

    public static long initRec(int srt, int end, int node){
        if (srt == end) return tree[node] = arr[srt];

        int mid = srt + (end - srt) / 2;
        long left = initRec(srt, mid, node * 2);
        long right = initRec(mid+1, end, node * 2 + 1);
        return tree[node] = left + right;
    }

    //
    public static void pushDown(int node, int srt, int end){
        int mid = srt + (end - srt) / 2;

        // push down
        // 자식 노드의 lazy 값을 갱신
        lazyExist[node*2] = lazyExist[node*2+1] = true;
        lazyValue[node*2] += lazyValue[node];
        lazyValue[node*2+1] += lazyValue[node];

        // 자식 노드의 실제 값을 갱신
        tree[node*2] += lazyValue[node] * (mid - srt + 1);
        tree[node*2+1] += lazyValue[node] * (end - mid);

        // 내려보냈음으로 현재 노드의 lazy 값 초기화
        lazyValue[node] = 0;
        lazyExist[node] = false;
    }

    // srt, end : 현재 배열 index 범위. left, right : 원래 구하고자 했던 배열 index 범위
    public static long query(int srt, int end, int node, int left, int right){
        if (end < left || right < srt) return 0;
        if (left <= srt && end <= right) return tree[node];

        // 만약 lazy 값을 가지고 있다면 push down
        if (lazyExist[node]){
            pushDown(node, srt, end); // 현재 노드의 lazy 값을 자식 노드에게 전파
        }

        int mid = srt + (end - srt) / 2;
        long valLeft = query(srt, mid, node * 2, left, right);
        long valRight = query(mid + 1, end, node * 2 + 1, left, right);
        return valLeft + valRight;
    }

    public static long update(int srt, int end, long addValue, int node, int left, int right){
        if (end < left || right < srt) return tree[node];

        // 현재 범위가 구하고자 하는 범위에 딱 들어가는 경우
        if (left <= srt && end <= right){
            lazyExist[node] = true;
            lazyValue[node] += addValue;
            return tree[node] += addValue * (end - srt + 1);
        }

        // 범위가 애매한데, 만약 lazy 값을 가지고 있는 노드라면 내려보냄
        if (lazyExist[node]){
            pushDown(node, srt, end); // 현재 노드의 lazy 값을 자식 노드에게 전파
        }

        int mid = srt + (end - srt) / 2;
        long valLeft = update(srt, mid, addValue, node * 2, left, right);
        long valRight = update(mid + 1, end, addValue, node * 2 + 1, left, right);
        return tree[node] = valLeft + valRight;

    }



}