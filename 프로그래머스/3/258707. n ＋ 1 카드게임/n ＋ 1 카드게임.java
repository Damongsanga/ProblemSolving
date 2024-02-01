import java.util.Arrays;

class Solution {

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int[] arr = new int[n + 1];

        int pair = 0;
        for (int i = 0; i < n / 3; i++) {
            arr[cards[i]] = 2;
            if (arr[n + 1 - cards[i]] == 2) pair++;
        }

        int round = 1;
        int c = coin;
        for (int i = n / 3; i < n; i += 2) {
            int l = cards[i];
            int r = cards[i + 1];
            arr[l] = 1;
            arr[r] = 1;

            if (arr[n + 1 - l] == 2 && c >= 1) {
                c--;
                arr[l] = 2;
                pair++;
            }

            if (arr[n + 1 - r] == 2 && c >= 1) {
                c--;
                arr[r] = 2;
                pair++;
            }

            if (pair < round) {
                for (int j = 1; j <= n; j++) {
                    if (arr[j] == 2 && arr[n + 1 - j] == 1 && c >= 1 ) {
                        arr[n + 1 - j] = 2;
                        c--;
                        pair++;
                        break;
                    }
                }

                for (int j = 1; j <= n/2; j++) {
                    if (arr[j] == 1 && arr[n + 1 - j] == 1 && c >= 2) {
                        arr[j] = 2;
                        arr[n+1-j] = 2;
                        c -= 2;
                        pair++;
                        break;
                    }
                }

                if (pair < round) break;
            }
            round++;
        }

        return round;
    }

}