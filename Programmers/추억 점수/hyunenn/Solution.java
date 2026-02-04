import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        for(int i=0;i<name.length;i++) {
            map.put(name[i], map.getOrDefault(name[i], 0) + yearning[i]);
        }

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<photo.length;i++) {
            int sum = 0;
            for(int j=0;j<photo[i].length;j++) {
                if(map.containsKey(photo[i][j])) {
                    sum += map.get(photo[i][j]);                }
            }
            list.add(sum);
        }

        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            answer[i] = list.get(i);
        }
        // for(Map.Entry<String, Integer> entry : map.entrySet()) {
        //     System.out.println("key : " + entry.getKey() + " , value : " + entry.getValue());
        // }

        return answer;
    }
}