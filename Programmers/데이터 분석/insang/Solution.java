import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        String[] sort = {"code", "date", "maximum", "remain"};

        int ext_idx = 0;
        int sort_idx = 0;
        for(int i = 0; i < sort.length; i++){
            if(sort[i].equals(ext)) ext_idx = i;
            if(sort[i].equals(sort_by)) sort_idx = i;
        }

        ArrayList<int[]> list = new ArrayList<>();
        for(int[] d : data){
            if(d[ext_idx] < val_ext) list.add(d);
        }

        int idx = sort_idx;
        list.sort((a, b) -> Integer.compare(a[idx], b[idx]));
        int[][] answer = new int[list.size()][4];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}