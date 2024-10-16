import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int ans;
    static int[] target;
    static int[][] scores;
    static List<int[]> scoreList;
    static PriorityQueue<int[]> rank;
    
    static void initializeData(int[][] scores) {
        N = scores.length;
        Solution.scores = scores;
        target = new int[] {scores[0][0], scores[0][1]};
        scoreList = new ArrayList<>();
        rank = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                return Integer.compare(e2[0], e1[0]);
            }
        });
        for (int i = 0; i < N; i++) {
            scoreList.add(new int[] {scores[i][0], scores[i][1]});
            rank.add(new int[] {scores[i][0] + scores[i][1], i});
        }
        Collections.sort(scoreList, (a1, a2) -> {
            return a1[0] - a2[0];
        });
        ans = 0;
    }
    
    static void solve() {
        if (isFailed(target[0], target[1])) {
            ans = -1;
            return ;
        }
        
        int currScore = -1;
        int duplicated = 0;
        
        while (!rank.isEmpty()) {
            int[] employee = rank.poll();
            int score = employee[0];
            int eIdx = employee[1];
            int attitudePoint = scores[eIdx][0];
            int peerPoint = scores[eIdx][1];
            
            if (isFailed(attitudePoint, peerPoint)) continue;
            
            if (score == currScore) {
                duplicated++;
            }
            else {
                currScore = score;
                while (duplicated > 0) {
                    ans++;
                    duplicated--;
                }
                
                ans++;
            }
            
            if (eIdx == 0) break;
        }
    }
    
    static boolean isFailed(int attitudePoint, int peerPoint) {
        int head = N - 1;
        int tail = 0;
        
        if (scoreList.get(head)[0] <= attitudePoint) return false; 
        
        while (head - tail > 1) {
            int point = tail + (head - tail) / 2;
            
            if (scoreList.get(point)[0] > attitudePoint) {
                head = point;
                continue;
            }
            
            tail = point;
        }
        
        if (scoreList.get(tail)[0] > attitudePoint) head = tail;
        
        while (head < N) {
            int peer = scoreList.get(head)[1];
            
            if (peer > peerPoint) return true;
            
            head++;
        }
        return false;
    }
    
    public int solution(int[][] scores) {
        initializeData(scores);
        solve();
        
        return ans;
    }
}