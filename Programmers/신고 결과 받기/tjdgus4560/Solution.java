import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i<id_list.length; i++){
            map.put(id_list[i], i);
        }

        boolean[][] arr = new boolean[id_list.length][id_list.length]; // 신고추적배열
        for(String s : report){
            String[] tmp = s.split(" ");
            arr[map.get(tmp[0])][map.get(tmp[1])] = true;
        }

        // for(int i=0; i<id_list.length; i++){
        //     for(int j=0; j<id_list.length; j++){
        //         if(arr[i][j]) System.out.print("1 ");
        //         else System.out.print("0 ");
        //     }
        //     System.out.println();
        // }

        for(int c=0; c<id_list.length; c++){
            int count = 0;
            for(int r=0; r<id_list.length; r++){
                if(arr[r][c]) count++;
                if(count>=k) break;
            }
            if(count<k) continue;
            for(int r=0; r<id_list.length; r++){
                if(arr[r][c]) {
                    answer[r]++;
                }
            }
        }
        return answer;
    }
}