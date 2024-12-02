class Solution {
    public int solution(String[] board) {
        int oCount = 0;
        int xCount = 0;
        
        for (String row : board) {
            for (char cell : row.toCharArray()) {
                if (cell == 'O') {
                    oCount++;
                } else if (cell == 'X') {
                    xCount++;
                }
            }
        }
        
        if (Math.abs(oCount - xCount) >= 2) {
            return 0;
        }

        if (xCount > oCount) {
            return 0;
        }
        
        boolean oWins = isWinner(board, 'O');
        boolean xWins = isWinner(board, 'X');
        
        if (oWins && oCount == xCount) {
            return 0;
        }
        
        if (xWins && oCount > xCount) {
            return 0;
        }
        
        return 1;
    }
    
    private boolean isWinner(String[] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == symbol && board[i].charAt(1) == symbol && board[i].charAt(2) == symbol) {
                return true;
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == symbol && board[1].charAt(i) == symbol && board[2].charAt(i) == symbol) {
                return true;
            }
        }
        
        if (board[0].charAt(0) == symbol && board[1].charAt(1) == symbol && board[2].charAt(2) == symbol) {
            return true;
        }
        if (board[0].charAt(2) == symbol && board[1].charAt(1) == symbol && board[2].charAt(0) == symbol) {
            return true;
        }
        
        return false;
    }
}
