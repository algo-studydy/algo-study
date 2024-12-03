class Solution {
    public int solution(String[] board) {
        char[][] map = new char[3][3];
        int countO = 0;
        int countX = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'O') {
                    countO++;
                } else if (map[i][j] == 'X') {
                    countX++;
                }
            }
        }

        // 1. O가 X+1보다 많으면 안됨
        if (countO > countX + 1) return 0;

        // 2. X가 O보다 많으면 안됨
        if (countX > countO) return 0;

        // 승리 상태 확인
        boolean winO = checkWin(map, 'O');
        boolean winX = checkWin(map, 'X');

        // 3. O와 X가 동시에 승리하면 안됨
        if (winO && winX) return 0;

        // 4. X가 승리했을 때 O의 개수가 X보다 많으면 안됨
        if (winX && countO > countX) return 0;

        // 5. O가 승리했을 때 O와 X의 개수가 같으면 안됨
        if (winO && countO == countX) return 0;

        return 1;
    }

    public boolean checkWin(char[][] map, char ox) {
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == ox && map[i][1] == ox && map[i][2] == ox) return true;
            if (map[0][i] == ox && map[1][i] == ox && map[2][i] == ox) return true;
        }

        if (map[0][0] == ox && map[1][1] == ox && map[2][2] == ox) return true;
        if (map[0][2] == ox && map[1][1] == ox && map[2][0] == ox) return true;

        return false;
    }
}
