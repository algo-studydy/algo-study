import java.util.*;

class Solution {
    public boolean[] check;

    public int solution(int[] cards) {
        check = new boolean[cards.length];
        List<Set<Integer>> li = new ArrayList<>();


        int flag = 1;
        int group = 0;
        int count = 0;
        li.add(new HashSet<>());
        while(true){
            // 이번에 넣을것이 set에 포함되어 있으면 다음그룹으로
            if(li.get(group).contains(cards[flag-1])){
                group++;
                li.add(new HashSet<>());
                continue;
            }
            // 새로운 그룹 플래그 찾기
            if(li.get(group).size() == 0){
                for(int i = 0 ; i<check.length ; i++){
                    if(!check[i]) flag = i+1;
                }
            }
            // 전부다 체크하면 종료
            if(count == cards.length){
                break;
            }

            // 현재 상자 처리
            li.get(group).add(cards[flag-1]);
            check[flag-1] = true;
            flag = cards[flag-1];
            count++;
        }

        // 결과 처리
        int[] Gsize = new int[li.size()];
        for(int i = 0 ; i < li.size() ; i++){
            Gsize[i] = li.get(i).size();
        }
        Arrays.sort(Gsize);

        int answer = Gsize[Gsize.length-1] * Gsize[Gsize.length-2];
        return answer;
    }
}