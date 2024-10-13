import java.util.*;
import java.io.*;

class Solution {
    static final int N = 5;
    static String[][] places;
    static int[] ans;
    static List<List<Point>> lList;
    
    static void initializeData(String[][] places) {
        Solution.places = places;
        ans = new int[N];
        
        lList = new ArrayList<>();
        for (int room = 0; room < N; room++) {
            lList.add(new ArrayList<>());            
        }
        
        for (int room = 0; room < N; room++) {
            List<Point> list = lList.get(room);
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!isPerson(room, r, c)) continue;
                    
                    list.add(new Point(r, c));
                }
            }
        }
    }
    
    static void solve() {
        for (int i = 0; i < N; i++) {
            ans[i] = isSeparated(i);
        }
    }
    
    static int isSeparated(int room) {
        List<Point> list = lList.get(room);
        
        for (int i = 0; i < list.size(); i++) {
            Point start = list.get(i);
            
            for (int j = i + 1; j < list.size(); j++) {
                Point target = list.get(j);
                int distance = distance(start, target);

                if (distance > 2) continue;
                if (distance == 1) {
                    return 0;
                }
                if (isConnected(room, start, target)) {
                    return 0;
                }
            }
        }
            
        return 1;
    }
    
    static boolean isConnected(int room, Point start, Point target) {
        if (start.x == target.x) {
            if (isPartition(room, start.x, start.y + 1)) return false;
            
            return true;
        }
        
        if (start.y == target.y) {
            if (isPartition(room, start.x + 1, start.y)) return false;
            
            return true;
        }
        
        if (isPartition(room, start.x, target.y) && isPartition(room, target.x, start.y))
            return false;
        
        return true;
    }
    
    static int distance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    
    static boolean isPartition(int room, int row, int col) {
        return places[room][row].charAt(col) == 'X';
    }
    
    static boolean isPerson(int room, int row, int col) {
        return places[room][row].charAt(col) == 'P';
    }
    
    public int[] solution(String[][] places) {
        initializeData(places);
        solve();
        
        return ans;
    }
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "x: " + x +
                " y: " + y;
        }
    }
}