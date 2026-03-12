import java.util.*;
class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        if(n == 1) return sticker[0];

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        // 첫번째 스티커를 떼는 경우
        dp1[0] = sticker[0];
        dp1[1] = sticker[0]; // 두번째 스티커는 뗄 수 없으니 첫번째 스티커 값으로 변경
        for (int i = 2; i < n - 1; i++) {
            // 한칸 전, (두칸 전 + 현재 스티커) 비교
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        // 첫번째 스티커를 떼지 않는 경우
        dp2[0] = 0;
        dp2[1] = sticker[1];
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

        // System.out.println(Arrays.toString(dp1));
        // System.out.println(Arrays.toString(dp2));
        int answer = Integer.max(dp1[n-2], dp2[n-1]);
        return answer;
    }
}