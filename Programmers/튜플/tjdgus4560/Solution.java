import java.util.*;

class Solution {
    public int[] solution(String s) {

        s = s.substring(2, s.length()-2); // 전체를 감싸는 {{}}제거

        String[] sarr = s.split("\\},\\{"); // },{ 기준으로 분할

        Arrays.sort(sarr, (String s1, String s2) -> s1.length() - s2.length()); //문자열 길이 순 정렬

        Set<Integer> numSet = new HashSet<>(); //중복 확인용 set
        ArrayList<Integer> ansList = new ArrayList<>(); // 정답용 리스트

        for(String tmps : sarr){
            String[] nums = tmps.split(","); // 22,33 형태의 문자열을 콤마 기준으로 분리
            for(String num : nums){
                int n = Integer.parseInt(num);
                if(!numSet.contains(n)){ // set에 존재하지 않는 숫자라면 새로운 숫자이므로 정답 튜플에 추가
                    numSet.add(n);
                    ansList.add(n);
                }
            }
        }

        //arrlist 배열로 돌리기
        int[] answer = new int[ansList.size()];
        for(int i=0; i<ansList.size(); i++){
            answer[i] = ansList.get(i);
        }
        return answer;
    }
}