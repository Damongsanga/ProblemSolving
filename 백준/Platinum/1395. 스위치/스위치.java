import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 스위치
 *  알고리즘 : 세그먼트 트리
 *  주요 포인트 :
 *  1. Lazy Propagation을 사용하여 업데이트 쿼리 처리
 *  2. 메모리 초과 이슈 ->
 *  3. 값을 바꾸는게 아닌 추가하는 것임으로 구현 방법에 신경써서 pushDown을 구현
 * */
public class Main {

    static int[] tree;
    static int[] treeOpp;
    static boolean[] lazyExist;
    static int N;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // tree에는 켜진 버튼 수가 저장됨, init이 불필요
        tree = new int[4*N];
        treeOpp = new int[4*N];
        lazyExist = new boolean[4*N];

        oppInitRec(1, N, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if (flag == 0){
                update(1, N, 1, left, right);
            } else {
                sb.append(query(1, N, 1, left, right)).append("\n");
            }
        }

        System.out.println(sb);

    }

    private static int oppInitRec(int left, int right, int node){
        if (left == right) return treeOpp[node] = 1;

        int mid = left + (right - left) / 2 ;
        int lValue = oppInitRec(left, mid, node * 2);
        int rValue = oppInitRec(mid + 1, right, node * 2 + 1);
        return treeOpp[node] = lValue + rValue;

    }

    private static void pushdown(int node){

        // 자식 노드의 lazy 값 갱신 (false -> true)
        lazyExist[node * 2] = !lazyExist[node * 2];
        lazyExist[node * 2 + 1] = !lazyExist[node * 2 + 1];

        // 자식 노드의 실제 값 갱신
        // pushdown인 순간 이미 자식 값들은 무조건 갱신해야 되는 상태!
        inverseTree(node * 2);
        inverseTree(node * 2 + 1);

        // 내려보냈음으로 초기화
        lazyExist[node] = false;

    }

    private static int inverseTree(int node) {
        return tree[node] = treeOpp[node] - tree[node];
    }

    private static int update(int nodeL, int nodeR, int node, int left, int right){
        if (nodeR < left || right < nodeL) return tree[node];

        if (left <= nodeL && nodeR <= right){
            lazyExist[node] = !lazyExist[node];
            return inverseTree(node);
        }

        if (lazyExist[node]){
            pushdown(node);
        }

        int mid = nodeL + (nodeR - nodeL) / 2;
        int valLeft = update(nodeL, mid, node * 2, left, right);
        int valRight = update(mid + 1, nodeR, node * 2 + 1, left, right);
        return tree[node] = valLeft + valRight;

    }

    private static int query(int nodeL, int nodeR, int node, int left, int right){
        if (nodeR < left || right < nodeL) return 0;
        if (left <= nodeL && nodeR <= right) return tree[node];

        if (lazyExist[node]){
            pushdown(node);
        }

        int mid = nodeL + (nodeR - nodeL) / 2;
        int valLeft = query(nodeL, mid, node * 2, left, right);
        int valRight = query(mid + 1, nodeR, node * 2 + 1, left, right);
        return valLeft + valRight;
    }

}