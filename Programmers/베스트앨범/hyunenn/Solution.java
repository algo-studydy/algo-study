import java.util.*;

class Solution {
    static class Point {
        int time; int idx;
        Point(int time, int idx) {
            this.time = time;
            this.idx = idx;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<genres.length;i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }



        // 1. 해시맵으로 key, value로 가장 많은 장르를 찾는다? -> map 으로 구성하고, list로 변환후 내림차순 정렬

        List<String> genreSum = new ArrayList<>(map.keySet());
        genreSum.sort((a, b) -> map.get(b) - map.get(a));

        // 2. 그 다음 노래 높은 순 + 고유 번호 낮은 순 으로 정렬
        Map<String, List<Point>> songs = new HashMap<>();
        for(int i=0;i<genres.length;i++) {
            List<Point> list = songs.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Point(plays[i], i));
            songs.put(genres[i], list);
        }

        List<Integer> ans = new ArrayList<>();

        for(String s : genreSum) {
            List<Point> ls = songs.get(s);
            ls.sort((a, b) -> {
                if(a.time == b.time) return a.idx - b.idx;
                return b.time - a.time;
            });

            int cnt = 0;
            while(!ls.isEmpty() && cnt < 2) {
                ans.add(ls.remove(0).idx);
                cnt++;
            }
        }

        int[] answer = new int[ans.size()];
        for(int i=0;i<answer.length;i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }
}