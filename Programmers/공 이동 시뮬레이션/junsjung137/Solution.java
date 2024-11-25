import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long minRow = x, maxRow = x;
        long minCol = y, maxCol = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            int dx = queries[i][1];

            if (command == 0) { // 왼쪽으로 이동
                maxCol = Math.min(m - 1, maxCol + dx);
                if (minCol != 0) minCol += dx;
                minCol = Math.max(0, minCol);
            } else if (command == 1) { // 오른쪽으로 이동
                minCol = Math.max(0, minCol - dx);
                if (maxCol != m - 1) maxCol -= dx;
                maxCol = Math.min(m - 1, maxCol);
            } else if (command == 2) { // 위로 이동
                maxRow = Math.min(n - 1, maxRow + dx);
                if (minRow != 0) minRow += dx;
                minRow = Math.max(0, minRow);
            } else if (command == 3) { // 아래로 이동
                minRow = Math.max(0, minRow - dx);
                if (maxRow != n - 1) maxRow -= dx;
                maxRow = Math.min(n - 1, maxRow);
            }

            if (minRow > maxRow || minCol > maxCol) {
                return 0;
            }
        }

        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }
}
