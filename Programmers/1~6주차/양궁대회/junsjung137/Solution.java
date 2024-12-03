import java.util.*;

class Solution {
    static int n, maxGap;
    static final int size = 11;
    static int[] info;
    static int[] answer, score;
    static boolean isWin;
    
    public int[] solution(int n, int[] info) {
        answer = new int[size];
        score = new int[size];
        Solution.info = info;
        Solution.n = n;
        isWin = false;
        maxGap = 0;
        
        simulation(size - 1, n);
        
        return maxGap == 0 ? new int[] {-1} : answer;
    }
    
    static void simulation(int idx, int remain) {
        if (remain == 0) {
            int gap = getScore();
            
            if (gap > 0) {
                if (maxGap > gap) return;
                
                if (maxGap < gap) {
                    maxGap = gap;
                    answer = Arrays.copyOf(score, size);
                    return;
                }
            }
            return;
        }

        if (idx == -1) return;

        for (int hit = remain; hit >= 0; hit--) {
            score[idx] = hit;
            simulation(idx - 1, remain - hit);
        }
    }
    
    static int getScore() {
        int aScore = 0;
        int rScore = 0;

        for (int i = 0; i < size; i++) {
            if (info[i] == 0 && score[i] == 0) continue;

            if (info[i] >= score[i]) {
                aScore += 10 - i;
                continue;
            }

            rScore += 10 - i;
        }
        return rScore - aScore;
    }
}