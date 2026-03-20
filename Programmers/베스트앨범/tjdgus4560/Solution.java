import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String,Integer> totalMap=new HashMap<>(); //총 재생수 관리 맵
        Map<String,ArrayList<int[]>> songMap=new HashMap<>(); // 장르별 각 노래 인덱스, 재생수 관리 맵

        for(int i=0; i<genres.length; i++){
            // 장르별 총 재생수 구하기
            totalMap.put(genres[i], totalMap.getOrDefault(genres[i], 0)+plays[i]);

            // 장르별로 각 노래의 인덱스번호와 재생수 배열 map 에 넣기
            if(!songMap.containsKey(genres[i])){
                songMap.put(genres[i], new ArrayList<>());
            }

            songMap.get(genres[i]).add(new int[]{i, plays[i]});
        }

        for(ArrayList<int[]> list : songMap.values()){
            // 정렬조건 2, 3 수행
            list.sort((a, b) -> {return b[1] - a[1];});
        }

        // 총 재생순 장르 정렬
        ArrayList<String> genreList=new ArrayList<>(totalMap.keySet());
        genreList.sort((a,b)->totalMap.get(b)-totalMap.get(a));

        ArrayList<Integer> answer=new ArrayList<>();

        // 각 장르별 최대 2개 노래 수록
        for(String genre : genreList) {
            List<int[]> songs=songMap.get(genre);
            for(int i=0; i<Math.min(2, songs.size()); i++) {
                answer.add(songs.get(i)[0]);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}