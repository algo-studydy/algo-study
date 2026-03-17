import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> extMap = new HashMap<>();
        extMap.put("code", 0);
        extMap.put("date", 1);
        extMap.put("maximum", 2);
        extMap.put("remain", 3);

        List<int[]> list = new ArrayList<>();

        int extNum = extMap.get(ext);

        for (int i = 0; i < data.length; i++) {
            if (data[i][extNum] < val_ext) {
                list.add(data[i]);
            }
        }

        int sortByNum = extMap.get(sort_by);

        list.sort((o1, o2) -> o1[sortByNum] - o2[sortByNum]);

        return list.toArray(int[][]::new);
    }
}