import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = {};
        Tri[] root = new Tri[10001];
        for(int i = 0; i < words.length; i++) {
            int length = words[i].length();
            if(root[length] == null) {
                root[length] = new Tri();
            }
            root[length].add(words[i]);
        }

        answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if(root[len] == null) answer[i] = 0;
            else answer[i] = root[queries[i].length()].find(queries[i]);
        }
        return answer;
    }

    class Tri {
        int cnt;
        Tri[] frontNext;
        Tri[] backNext;

        Tri() {
            this.cnt = 0;
            frontNext = new Tri[26];
            backNext = new Tri[26];
        }

        public void add(String s) {
            this.cnt++;
            addFront(s);
            addBack(s);
        }

        private void addFront(String s) {
            Tri cur = this;
            for(char ch : s.toCharArray()) {
                if(cur.frontNext[ch-'a'] == null) {
                    cur.frontNext[ch-'a'] = new Tri();
                    cur.frontNext[ch-'a'].cnt = 1;
                }
                else {
                    cur.frontNext[ch-'a'].cnt++;
                }
                cur = cur.frontNext[ch-'a'];
            }
        }

        private void addBack(String s) {
            Tri cur = this;
            for(int i = s.length()-1; i >= 0; i--) {
                char ch = s.charAt(i);
                if(cur.backNext[ch-'a'] == null) {
                    cur.backNext[ch-'a'] = new Tri();
                    cur.backNext[ch-'a'].cnt = 1;
                }
                else {
                    cur.backNext[ch-'a'].cnt++;
                }
                cur = cur.backNext[ch-'a'];
            }
        }

        public int find(String s) {
            if(s.startsWith("?")) {
                return findBack(s);
            }
            else {
                return findFront(s);
            }
        }

        private int findFront(String s) {    
            Tri t = this;           
            for(int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if(ch == '?') return t.cnt;
                if(t.frontNext[ch-'a'] == null) return 0;
                t = t.frontNext[ch-'a'];                
            }
            return 0;
        }

        private int findBack(String s) {   
            Tri t = this;
            for(int i = s.length()-1; i >= 0; i--) {
                char ch = s.charAt(i);
                if(ch == '?') return t.cnt;
                if(t.backNext[ch-'a'] == null) return 0;
                t = t.backNext[ch-'a'];             
            }
            return 0;
        }
    }
}