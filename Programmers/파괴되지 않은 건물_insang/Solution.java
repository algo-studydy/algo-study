import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] map = new int[n+1][m+1];  // 누적합을 위한 2차원 배열 생성

        for (int i = 0; i < skill.length; i++){  // skill 배열의 길이 만큼 반복
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][0] == 1 ? skill[i][5] * -1 : skill[i][5];

            map[r1][c1] += degree;
            map[r2+1][c2+1] += degree;
            map[r1][c2+1] -= degree;
            map[r2+1][c1] -= degree;

        }

        // 위에서 아래로 누적합 진행
        for(int i = 1; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] += map[i-1][j];
            }
        }

        // 왼쪽에서 오른쪽으로 누적합 진행
        for(int i = 0; i < map.length; i++){
            for(int j = 1; j < map[0].length; j++){
                map[i][j] += map[i][j-1];
            }
        }

        for (int i = 0; i < map.length-1; i++){
            for (int j = 0; j < map[i].length-1; j++){
                if(board[i][j] + map[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}