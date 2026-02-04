import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < name.length; i++){
            map.put(name[i], yearning[i]);
        }

        int idx = 0;
        for(String[] p : photo){
            int score = 0;
            for(String people : p){
                score += map.getOrDefault(people, 0);
            }
            answer[idx++] = score;
        }
        return answer;
    }
}