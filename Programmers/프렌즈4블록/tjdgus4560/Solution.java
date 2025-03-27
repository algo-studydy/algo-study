import java.util.*;

class Solution {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int answer = 0;

        while (true) {
            // 1. 지울거 찾기
            List<Point> delList = new ArrayList<>();

            for (int i=0; i<m-1; i++) {
                for (int j=0; j<n-1; j++) {
                    char c = map[i][j];
                    if (c != '-' && c == map[i+1][j] && c == map[i][j+1] && c == map[i+1][j+1]) {
                        delList.add(new Point(i, j));
                        delList.add(new Point(i+1, j));
                        delList.add(new Point(i, j+1));
                        delList.add(new Point(i+1, j+1));
                    }
                }
            }

            // 제거할거 없으면 끝
            if (delList.isEmpty()) break;

            // 2. 블록 제거
            for (Point p : delList) {
                if (map[p.x][p.y] != '-') {
                    map[p.x][p.y] = '-';
                    answer++;
                }
            }

            // 3. 아래로 떨어뜨리기
            for (int i=m-1; i>=0; i--) {
                for (int j=n-1; j>=0; j--) {
                    if (map[i][j] == '-') {
                        for (int k=i-1; k>=0; k--) { // 위쪽 블록 찾기
                            if (map[k][j] != '-') {
                                map[i][j] = map[k][j];
                                map[k][j] = '-';
                                break;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }
}