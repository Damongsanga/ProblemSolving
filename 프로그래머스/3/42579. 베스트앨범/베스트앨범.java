import java.util.*;

class Solution {
    static class Song{
        int idx;
        String genre;
        int n;

        public Song(int idx, String genre, int n) {
            this.idx = idx;
            this.genre = genre;
            this.n = n;
        }

    }

    static Map<String, Integer> map;
    public int[] solution(String[] genres, int[] plays) {
        map = new HashMap<>();
        List<Song> songList = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            map.putIfAbsent(genres[i], 0);
            map.put(genres[i], map.get(genres[i]) + plays[i]);
            songList.add(new Song(i, genres[i], plays[i]));
        }

        Collections.sort(songList, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                if (map.get(o2.genre) == map.get(o1.genre)){
                    if (o2.n == o1.n)
                        return o1.idx - o2.idx;
                    return o2.n - o1.n;
                }
                return map.get(o2.genre) - map.get(o1.genre);
            }
        });

        List<Integer> answerList = new ArrayList<>();
        int count = 0;
        String prevGenre = "";
        for(Song s : songList){
            if (s.genre.equals(prevGenre)){
                if (count < 2){
                    answerList.add(s.idx);
                    count++;
                }
            } else {
                answerList.add(s.idx);
                count = 1;
                prevGenre = s.genre;
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;

    }
}