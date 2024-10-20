import java.io.*;
import java.util.*;

class Solution {
    static HashMap<String, ArrayList<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();

        //각 info에 대한 모든경우의 query 생성
        for(String i : info) {
            String[] data = i.split(" ");
            dfs(data,0,"");
        }

        // 점수들 오름차순 정렬
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }


        for(int i = 0; i < query.length; i++) {
            answer[i] = queryResult(query[i]);
        }


        return answer;
    }

    private int queryResult(String s) {
        int lastSpace = s.lastIndexOf(' ');
        String query = s.substring(0,lastSpace);
        int score = Integer.parseInt(s.substring(lastSpace+1));
        ArrayList<Integer> values = map.get(query);
        if(values != null) {
            int pos = binarySearch(values, score);
            return values.size() - pos;
        }
        return 0;
    }

    private int binarySearch(ArrayList<Integer> values, int target) {
        int leftIdx = 0;
        int rightIdx = values.size() - 1;

        while (leftIdx <= rightIdx) {
            int mid = (leftIdx + rightIdx) / 2;
            if (values.get(mid) >= target) {
                rightIdx = mid - 1;
            } else {
                leftIdx = mid + 1;
            }
        }
        return leftIdx;
    }

    public void dfs(String[] data, int n, String query){
        StringBuilder sb = new StringBuilder(query);
        StringBuilder sb2 = new StringBuilder(query);
        if(n == data.length - 1){
            if(map.containsKey(sb.toString())){
                map.get(sb.toString()).add(Integer.valueOf(data[n]));
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.valueOf(data[n]));
                map.put(sb.toString(), list);
            }
            return;
        }
        if(n>0){
            sb.append(" and ");
            sb2.append(" and ");
        }
        dfs(data,n+1,sb.append(data[n]).toString());
        dfs(data,n+1,sb2.append("-").toString());
    }
}