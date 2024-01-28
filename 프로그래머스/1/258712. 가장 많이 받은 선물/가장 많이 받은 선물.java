import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        Map<String, Integer> friendsIdMap = new HashMap<>();
        int id = 0;
        for (String friend : friends) {
            friendsIdMap.putIfAbsent(friend, id++);
        }

        int[] points = new int[N];
        int[][] giftRelations = new int[N][N];
        for (String gift : gifts) {
            String[] tmp = gift.split(" ");
            int fromId = friendsIdMap.get(tmp[0]);
            int toId = friendsIdMap.get(tmp[1]);
            points[fromId]++;
            points[toId]--;
            giftRelations[fromId][toId]++;
            giftRelations[toId][fromId]--;
        }

        int[] nextMonthGifts = new int[N];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (giftRelations[i][j] == 0) {
                    if (points[i] == points[j]) continue;
                    if (points[i] > points[j])
                        answer = Math.max(++nextMonthGifts[i], answer);
                    else
                        answer = Math.max(++nextMonthGifts[j], answer);
                } else {
                    if (giftRelations[i][j] > 0)
                        answer = Math.max(++nextMonthGifts[i], answer);
                    else
                        answer = Math.max(++nextMonthGifts[j], answer);
                }
            }
        }


        return answer;
    }
}