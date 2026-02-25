import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int r = park.length;
        int c = park[0].length;
        Arrays.sort(mats);

        // 가장 큰 돗자리부터 탐색
        for (int m = mats.length - 1; m >= 0; m--) {
            int size = mats[m];

            // size 크기의 돗자리를 놓을 수 있는지 탐색
            for (int i = 0; i <= r - size; i++) {
                for (int j = 0; j <= c - size; j++) {

                    boolean flag = true;

                    for (int row = i; row < i + size; row++) {
                        for (int col = j; col < j + size; col++) {
                            if (!park[row][col].equals("-1")) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) break;
                    }

                    if (flag) {
                        return size;
                    }
                }
            }
        }
        return -1;
    }
}