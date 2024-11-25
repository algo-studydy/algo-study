import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        int answer = 0;
        double missilePosition = -1;

        for (int[] target : targets) {
            double s = target[0];
            double e = target[1];

            if (missilePosition < s) {
                answer++;
                missilePosition = e - 0.1;
            }
        }

        return answer;
    }
}
