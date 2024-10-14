import java.util.*;

class Solution {
    // skill [type, r1, c1, r2, c2, degree]
    public int solution(int[][] board, int[][] skills) {
        int count = 0;
        for(int[] skill : skills){
            for(int i = skill[1] ; i <= skill[3] ; i++){
                for(int j = skill[2] ; j <= skill[4] ; j++){
                    //공격
                    if(skill[0] == 1){
                        board[i][j]-=skill[5];
                    }else{
                        board[i][j]+=skill[5];
                    }
                }
            }
        }

        for(int [] b : board){
            for(int health : b){
                if(health > 0){
                    count++;
                }
            }
        }
        int answer = count;
        return answer;
    }
}