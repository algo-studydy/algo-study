import java.util.*;

class Solution {
    static int rSize, cSize;
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        rSize = park.length;
        cSize = park[0].length;
        // 매 칸 마다, 현재 자리에서 매트를 필수 있는지 체크
        // O(50 * 50 * 10)
        for(int i=0;i<park.length;i++) {
            for(int j=0;j<park[0].length;j++) {
                // 빈 자리가 아니라면, 스킵
                if(!park[i][j].equals("-1")) continue;

                for(int k=0;k<mats.length;k++) {
                    // 현재 자리에서 매트를 필수 있는지 검사
                    if(check(i, j, mats[k], park)) {
                        answer = Math.max(answer, mats[k]);
                    }
                }
            }
        }
        return answer;
    }

    private static boolean check(int r, int c, int n, String[][] park) {
        if(r + n - 1 >= rSize || c + n - 1 >= cSize)
            return false;
        for(int i=r;i<r+n;i++) {
            for(int j=c;j<c+n;j++) {
                if(!park[i][j].equals("-1")) return false;
            }
        }

        return true;
    }
}