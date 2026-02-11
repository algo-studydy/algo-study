class Solution {
    int N;
    boolean[][] board;
    int answer = 0;
    public int solution(int n) {
        this.N = n;
        board = new boolean[n][n];
        re(0);

        return answer;
    }

    public void re(int r){
        if(r == N){
            answer++;
            return;
        }

        for(int c=0; c<N; c++){
            if(isVaild(r,c)){
                board[r][c] = true;
                re(r+1);
                board[r][c] = false;
            }
        }
    }

    public boolean isVaild(int R, int C){

        // 세로검증
        int r = R;
        int c = C;
        while(r>=0){
            if(board[r--][c]){
                return false;
            }
        }

        // 왼쪽 대각선검증
        r = R;
        c = C;
        while(r>=0 && c>=0){
            if(board[r--][c--]){
                return false;
            }
        }

        // 오른쪽 대각선검증
        r = R;
        c = C;
        while(r>=0 && c<N){
            if(board[r--][c++]){
                return false;
            }
        }

        return true;
    }
}