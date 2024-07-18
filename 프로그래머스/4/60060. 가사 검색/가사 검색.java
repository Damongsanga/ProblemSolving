import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        // 인풋
        Map<Integer, List<String>> frontMap = new HashMap<>();
        Map<Integer, List<String>> backMap = new HashMap<>();
        for (String word : words){
            int len = word.length();
            frontMap.putIfAbsent(len, new ArrayList<>());
            frontMap.get(len).add(word);
            backMap.putIfAbsent(len, new ArrayList<>());
            backMap.get(len).add(new StringBuilder(word).reverse().toString());
        }

        // 정렬
        for (List<String> list :frontMap.values()){
            Collections.sort(list);
        }
        for (List<String> list :backMap.values()){
            Collections.sort(list);
        }

        // 쿼리
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.charAt(0) != '?') answer[i] = find(query, frontMap);
            else answer[i] = find(new StringBuilder(query).reverse().toString(), backMap);
        }

        return answer;
    }

    private int find(String query, Map<Integer, List<String>> frontMap) {
        List<String> list = frontMap.get(query.length());
        if (list == null) return 0;
        int found = search(query, list);
        if (found == -1) return 0;

        return searchRight(query, list, found) - searchLeft(query, list, found) + 1;

    }

    private int searchRight(String query, List<String> list, int l) {
        int r = list.size()-1;
        int rightIdxBoundary = l;
        while(l <= r){
            int mid = l + (r-l) / 2;
            int res = compare(list.get(mid), query);
            if (res == 0) {
                rightIdxBoundary = mid;
                l = mid+1;
            } else if (res == 1) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return rightIdxBoundary;
    }

    private int searchLeft(String query, List<String> list, int r) {
        int l = 0;
        int leftIdxBoundary = r;
        
        while(l <= r){
            int mid = l + (r-l) / 2;
            int res = compare(list.get(mid), query);
            if (res == 0) {
                leftIdxBoundary = mid;
                r = mid-1;
            } else if (res == 1) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return leftIdxBoundary;
    }

    private int search(String query, List<String> list) {
        int l = 0;
        int r = list.size()-1;
        int foundIdx = -1;

        while(l <= r){
            int mid = l + (r-l) / 2;
            int res = compare(list.get(mid), query);
            if (res == 0) {
                foundIdx = mid;
                break;
            } else if (res == 1) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return foundIdx;
    }


    private int compare(String word, String query){
        for (int i = 0; i < word.length(); i++) {
            if (query.charAt(i) == '?') return 0; // 쿼리에 해당하는 단어면 0 반환
            if (word.charAt(i) > query.charAt(i)) return 1; // 쿼리보다 뒷단어면 1
            else if (word.charAt(i) < query.charAt(i)) return -1; // 쿼리보다 앞단어면 -1
        }
        return 0;
    }


}