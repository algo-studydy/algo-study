class Solution {
    public boolean result(String[] board, char player){
        for(int i = 0; i < 3; i++){
            boolean flag = true;
            // 가로 체크
            for(int j = 0; j < 3; j++){
                if(board[i].charAt(j) != player) {
                    flag = false;
                    break;
                }
            }
            // 가로 방향으로 승리했으면 true 리턴
            if(flag) return true;
            else flag = true;

            // 세로 체크
            for(int j = 0; j < 3; j++){
                if(board[j].charAt(i) != player){
                    flag = false;
                    break;
                }
            }
            // 세로 방향으로 승리했으면 true 리턴
            if(flag) return true;
        }
        // 대각선 체크
        if(board[0].charAt(0) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(2) == player) return true;

        if(board[0].charAt(2) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(0) == player) return true;
        return false;
    }

    public int solution(String[] board) {
        int answer = 1;
        int oCnt = 0;
        int xCnt = 0;

        // O와 X의 개수 카운트
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i].charAt(j) == 'O') oCnt++;
                else if(board[i].charAt(j) == 'X') xCnt++;
            }
        }

        // X의 개수가 O보다 많으면 안됨
        if(xCnt > oCnt) return 0;

        // O의 개수가 X+1 보다 많으면 안됨
        if(oCnt > xCnt + 1) return 0;

        // O가 이겼는데 X의 개수가 O의 개수 이상이면 안됨
        if(result(board, 'O') && xCnt >= oCnt) return 0;

        // X가 이겼는데 O의 개수가 X의 개수보다 크면 안됨
        if(result(board, 'X') && oCnt > xCnt) return 0;
        return answer;
    }
}