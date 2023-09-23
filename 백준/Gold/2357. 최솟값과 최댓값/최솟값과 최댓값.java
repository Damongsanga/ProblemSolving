import java.io.*;
import java.util.*;

class SegmentTreeMax{
    int N;
    int[] tree;

    // 해당 세그먼트 트리의 연산
    int merge(int l, int r){
        return Math.max(l,r);
    }

    SegmentTreeMax(int[] arr, int size){
        this.N = size;
        tree = new int[N*4];
        buildRec(arr, 1, 0, N-1);
    }

    // node는 tree 배열의 index, l, r은 해당 노드가 실제로 커버하고 있는 값의 범위
    int buildRec(int[] arr, int node, int nodeLeft, int nodeRight){
        // 만약 범위가 1칸으로 줄어들었을 때 (리프노드일 때), 그 범위에 해당하는 값을 tree 배열에 넣어줌
        if (nodeLeft == nodeRight){
            return tree[node] = arr[nodeLeft];
        }

        int mid = nodeLeft + (nodeRight-nodeLeft)/2;
        // 왼쪽 자식의 값을 찾음 (자식 인덱스는 2배, 해당 노드가 커버하는 범위는 left ~ mid임)
        int leftVal = buildRec(arr, node*2, nodeLeft, mid);
        // 오른쪽 자식의 값을 찾음 (자식 인덱스는 2배+1, 해당 노드가 커버하는 범위는 mid ~ right임)
        int rightVal = buildRec(arr, node*2+1, mid+1, nodeRight);

        // 두 자식의 값을 merge
        return tree[node] = merge(leftVal,rightVal);

    }

    int query(int l, int r){
        return queryRec(l, r, 1, 0, N-1);
    }

    int queryRec(int l, int r, int node, int nodeLeft, int nodeRight){
        // 해당 값이 범위를 아예 벗어나버리면 연산에 무관한 값을 리턴
        if (r < nodeLeft || nodeRight < l) return Integer.MIN_VALUE;

        // 범위에 정확히 들어왔다면 그 값을 리턴
        if(l <= nodeLeft && nodeRight <= r) return tree[node];

        // 범위에 걸쳐서 받았다면 쪼개서 다시 탐색
        int mid = nodeLeft + (nodeRight - nodeLeft)/2;
        return merge(queryRec(l, r, node*2, nodeLeft, mid), queryRec(l,r,node*2+1, mid+1, nodeRight));

    }

    void update(int idx, int newValue){
        updateRec(idx, newValue, 1,0,N-1);
    }

    int updateRec(int idx, int newValue, int node, int nodeLeft, int nodeRight){

        // 업데이트할 위치를 포함하지 않는 경우 해당값 그대로 리턴(추가 탐색 없음, 업데이트 되지 않음)
        if (idx < nodeLeft || nodeRight < idx) return tree[node];

        // 해당 위치를 찾은 경우 값을 갱신 후 위로 리턴
        if (nodeLeft == nodeRight) return tree[node] = newValue;

        // 해당 위치를 포함한 경우 아래로 추가 탐색, 찾은 값을 갱신
        int mid = nodeLeft + (nodeRight - nodeLeft)/2;
        int leftVal = updateRec(idx, newValue, node*2, nodeLeft, mid);
        int rightVal = updateRec(idx, newValue, node*2+1, mid, nodeRight);
        return tree[node] = merge(leftVal, rightVal);

    }

}

class SegmentTreeMin{
    int N;
    int[] tree;

    // 해당 세그먼트 트리의 연산
    int merge(int l, int r){
        return Math.min(l,r);
    }

    SegmentTreeMin(int[] arr, int size){
        this.N = size;
        tree = new int[N*4];
        buildRec(arr, 1, 0, N-1);
    }

    // node는 tree 배열의 index, l, r은 해당 노드가 실제로 커버하고 있는 값의 범위
    int buildRec(int[] arr, int node, int nodeLeft, int nodeRight){
        // 만약 범위가 1칸으로 줄어들었을 때 (리프노드일 때), 그 범위에 해당하는 값을 tree 배열에 넣어줌
        if (nodeLeft == nodeRight){
            return tree[node] = arr[nodeLeft];
        }

        int mid = nodeLeft + (nodeRight-nodeLeft)/2;
        // 왼쪽 자식의 값을 찾음 (자식 인덱스는 2배, 해당 노드가 커버하는 범위는 left ~ mid임)
        int leftVal = buildRec(arr, node*2, nodeLeft, mid);
        // 오른쪽 자식의 값을 찾음 (자식 인덱스는 2배+1, 해당 노드가 커버하는 범위는 mid ~ right임)
        int rightVal = buildRec(arr, node*2+1, mid+1, nodeRight);

        // 두 자식의 값을 merge
        return tree[node] = merge(leftVal,rightVal);

    }

    int query(int l, int r){
        return queryRec(l, r, 1, 0, N-1);
    }

    int queryRec(int l, int r, int node, int nodeLeft, int nodeRight){
        // 해당 값이 범위를 아예 벗어나버리면 연산에 무관한 값을 리턴
        if (r < nodeLeft || nodeRight < l) return Integer.MAX_VALUE;

        // 범위에 정확히 들어왔다면 그 값을 리턴
        if(l <= nodeLeft && nodeRight <= r) return tree[node];

        // 범위에 걸쳐서 받았다면 쪼개서 다시 탐색
        int mid = nodeLeft + (nodeRight - nodeLeft)/2;
        return merge(queryRec(l, r, node*2, nodeLeft, mid), queryRec(l,r,node*2+1, mid+1, nodeRight));

    }

    void update(int idx, int newValue){
        updateRec(idx, newValue, 1,0,N-1);
    }

    int updateRec(int idx, int newValue, int node, int nodeLeft, int nodeRight){

        // 업데이트할 위치를 포함하지 않는 경우 해당값 그대로 리턴(추가 탐색 없음, 업데이트 되지 않음)
        if (idx < nodeLeft || nodeRight < idx) return tree[node];

        // 해당 위치를 찾은 경우 값을 갱신 후 위로 리턴
        if (nodeLeft == nodeRight) return tree[node] = newValue;

        // 해당 위치를 포함한 경우 아래로 추가 탐색, 찾은 값을 갱신
        int mid = nodeLeft + (nodeRight - nodeLeft)/2;
        int leftVal = updateRec(idx, newValue, node*2, nodeLeft, mid);
        int rightVal = updateRec(idx, newValue, node*2+1, mid, nodeRight);
        return tree[node] = merge(leftVal, rightVal);

    }

}






public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTreeMax sgmtMax = new SegmentTreeMax(arr, N);
        SegmentTreeMin sgmtMin = new SegmentTreeMin(arr, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            sb.append(sgmtMin.query(l,r)  + " ");
            sb.append(sgmtMax.query(l,r) + "\n");
        }
        System.out.println(sb);

    }
}