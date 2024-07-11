import java.util.*;

class Solution {
    static int N = 50;
    static int[] parents = new int[N*N+1];
    String[][] arr = new String[N][N];

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        // 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = "EMPTY";
                parents[getId(i,j)] = getId(i,j);
            }
        }

        for (String command : commands) {
            String[] tmp = command.split(" ");
            switch (tmp[0]) {
                case "UPDATE" -> {
                    if (tmp.length == 4) update(Integer.parseInt(tmp[1])-1, Integer.parseInt(tmp[2])-1, tmp[3]);
                    else updateValue(tmp[1], tmp[2]);
                }
                case "MERGE" -> merge(Integer.parseInt(tmp[1])-1, Integer.parseInt(tmp[2])-1, Integer.parseInt(tmp[3]) - 1, Integer.parseInt(tmp[4]) - 1);
                case "UNMERGE" -> unmerge(Integer.parseInt(tmp[1]) - 1, Integer.parseInt(tmp[2]) - 1);
                case "PRINT" -> print(Integer.parseInt(tmp[1]) - 1, Integer.parseInt(tmp[2]) - 1, answer);
            }
        }

        return answer.toArray(new String[0]);
    }

    private void update(int r, int c, String newValue){
        int parentId = find(getId(r,c));
        arr[parentId/N][parentId%N] = newValue;
    }

    private void updateValue(String value, String newValue){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j].equals(value)) arr[i][j] = newValue;
            }
        }
    }

    private void merge(int r1, int c1, int r2, int c2){
        if (r1 == r2 && c1 == c2) return;
        if (!getValueByRC(r1, c1).equals("EMPTY")){
            union(getId(r1,c1), getId(r2,c2));
        } else {
            union(getId(r2,c2),getId(r1,c1));
        }
    }


    private void unmerge(int r, int c){
        int parentId = find(getId(r,c));
        String value = arr[parentId/N][parentId%N];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N*N; i++) {
            if (find(i) == parentId) set.add(i);
        }

        for (Integer id : set) {
            parents[id] = id; // 그룹 초기화
            arr[id/N][id%N] = "EMPTY";
        }

        arr[r][c] = value;

    }

    private void print(int r, int c, List<String> answer){
        answer.add(getValueByRC(r,c));
    }


    // a가 부모가 됨
    private boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB){
            return false;
        }
        parents[parentB] = parentA;
        return true;
    }

    private int find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private int getId(int r, int c){
        return r * N + c;
    }

    private String getValueById(int id){
        int parentId = find(id);
        return arr[parentId/N][parentId%N];
    }

    private String getValueByRC(int r, int c){
        int parentId = find(getId(r,c));
        return getValueById(parentId);
    }





}