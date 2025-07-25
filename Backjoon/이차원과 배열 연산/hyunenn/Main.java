import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, K, nowR, nowC;
    static int[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());
        map = new int[3][3];
        int ans = Integer.MAX_VALUE;
        nowR = 3; nowC = 3;
        for(int i=0;i<3;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int t = 0; t <= 100; t++) {
            if (R < nowR && C < nowC && map[R][C] == K) {
                System.out.println(t);
                return;
            }

            if (nowR >= nowC) {
                calR();
            } else {
                calC();
            }
        }
        System.out.println(-1);
    }

    public static void calR() {
        List<List<Integer>> newRows = new ArrayList<>();
        int maxCol = 0;
        for(int i=0;i<nowR;i++) {
            Map<Integer, Integer> hashMap = new HashMap<>();
            for(int j=0;j<nowC;j++) {
                if(map[i][j] == 0) continue;
                hashMap.put(map[i][j], hashMap.getOrDefault(map[i][j], 0) + 1);
            }

            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashMap.entrySet());
            list.sort((a, b) -> {
                if(a.getValue().equals(b.getValue())) {
                    return a.getKey() - b.getKey();
                }
                return a.getValue() - b.getValue();
            });

            List<Integer> newRow = new ArrayList<>();
            for(int k=0;k<list.size();k++) {
                newRow.add(list.get(k).getKey());
                newRow.add(list.get(k).getValue());
            }
            maxCol = Math.max(maxCol, newRow.size());
            newRows.add(newRow);
        }

        nowC = Math.min(maxCol, 100);
        map = new int[nowR][nowC];

        for(int i=0;i<nowR;i++) {
            List<Integer> row = newRows.get(i);
            for(int j=0;j<row.size() && j < 100 ;j++) {
                map[i][j] = row.get(j);
            }
        }
    }

    public static void calC() {
        List<List<Integer>> newCols = new ArrayList<>();
        int maxRow = 0;
        for(int j=0;j<nowC;j++) {
            Map<Integer, Integer> hashMap = new HashMap<>();
            for(int i=0;i<nowR;i++) {
                if(map[i][j] == 0) continue;
                hashMap.put(map[i][j], hashMap.getOrDefault(map[i][j], 0) + 1);
            }

            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashMap.entrySet());
            list.sort((a, b) -> {
                if(a.getValue().equals(b.getValue())) {
                    return a.getKey() - b.getKey();
                }
                return a.getValue() - b.getValue();
            });

            List<Integer> newCol = new ArrayList<>();
            for(int k=0;k<list.size();k++) {
                newCol.add(list.get(k).getKey());
                newCol.add(list.get(k).getValue());
            }
            maxRow = Math.max(maxRow, newCol.size());
            newCols.add(newCol);
        }

        nowR = Math.min(maxRow, 100);
        map = new int[nowR][nowC];

        for(int j=0;j<nowC;j++) {
            List<Integer> col = newCols.get(j);
            for(int i=0;i<col.size() && i < 100 ;i++) {
                map[i][j] = col.get(i);
            }
        }
    }
}
