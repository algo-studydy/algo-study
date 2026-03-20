import java.util.*;

class Solution {
    static class song {
        int id;
        int play;

        public song(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        // 장르별 총 재생 횟수
        Map<String, Integer> genreCnt = new HashMap<>();
        // 장르별 재생 횟수
        Map<String, List<song>> genreSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreCnt.put(genres[i], genreCnt.getOrDefault(genres[i], 0) + plays[i]);

            if (!genreSongs.containsKey(genres[i])) {
                genreSongs.put(genres[i], new ArrayList<>());
            }
            genreSongs.get(genres[i]).add(new song(i, plays[i]));
        }

        List<String> genreSort = new ArrayList<>();
        for (String key : genreCnt.keySet()) {
            genreSort.add(key);
        }
        // 총 재생 횟수를 기준으로 장르 내림차순 정렬
        genreSort.sort((g1, g2) -> genreCnt.get(g2) - genreCnt.get(g1));

        List<Integer> result = new ArrayList<>();

        // 정렬된 장르 순서대로 노래 추출
        for (String genre : genreSort) {
            List<song> songs = genreSongs.get(genre);

            // 장르 내 노래 정렬 (재생 횟수 내림차순 -> id 오름차순)
            songs.sort((s1, s2) -> {
                if (s1.play == s2.play) {
                    return s1.id - s2.id;
                }
                return s2.play - s1.play;
            });

            // 최대 2곡까지 결과에 추가
            result.add(songs.get(0).id);
            if (songs.size() > 1) {
                result.add(songs.get(1).id);
            }
        }

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}