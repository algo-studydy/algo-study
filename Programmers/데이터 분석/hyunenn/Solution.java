import java.util.*;
import java.io.*;

class Solution {
    static class Pair {
        int code, date, max, rem;
        Pair(int code, int date, int max, int rem) {
            this.code = code;
            this.date = date;
            this.max = max;
            this.rem = rem;
        }
    }
    static List<Pair> list = new ArrayList<>();
    static Comparator<Pair> comparator;
    static String[] loc = new String[] {"code", "date", "maximum", "remain"};
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // ext를 기반으로 선 탐색 후, 해당하는 번째의 수에서 val_ext보다 작은 값들을 list에 저장
        // 저장된 값들에서 sort_by에 해당하는 번째의 수를 기준으로 오름차순 정렬
        int target = 0;
        int sorting = 0;
        for(int i=0;i<data.length;i++) {
            for(int j=0;j<4;j++) {
                if(loc[j].equals(ext))
                    target = j;
                if(loc[j].equals(sort_by))
                    sorting = j;
            }
            // val_ext보다 작은 값들 리스트에 저장
            if(data[i][target] < val_ext) {
                list.add(new Pair(data[i][0], data[i][1], data[i][2], data[i][3]));
            }

        }
        for(int i=0;i<list.size();i++) {
            Pair p = list.get(i);
        }

        // 정렬조건에 해당하는 값을 기준으로 정렬
        switch (sorting) {
            case 0:
                comparator = new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2) {
                        return Integer.compare(p1.code, p2.code);
                    }
                };
                break;
            case 1:
                comparator = new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2) {
                        return Integer.compare(p1.date, p2.date);
                    }
                };
                break;
            case 2:
                comparator = new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2) {
                        return Integer.compare(p1.max, p2.max);
                    }
                };
                break;
            case 3:
                comparator = new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p1, Pair p2) {
                        return Integer.compare(p1.rem, p2.rem);
                    }
                };
                break;
        }

        list.sort(comparator);
        int[][] answer = new int[list.size()][4];
        for(int i=0;i<list.size();i++) {
            Pair p = list.get(i);
            for(int j=0;j<4;j++) {
                answer[i][0] = p.code;
                answer[i][1] = p.date;
                answer[i][2] = p.max;
                answer[i][3] = p.rem;
            }
        }
        return answer;
    }
}