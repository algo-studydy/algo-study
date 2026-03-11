class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int r = bill[0];
        int c = bill[1];
        while(true) {
            // 1. 바로 지갑에 들어가는지 체크
            if(wallet[0] >= r && wallet[1] >= c) break;
            if(wallet[0] >= c && wallet[1] >= r) break;
            // 2. 안 맞는다면 가로, 세로 중 가장 큰 걸 나눈다.
            if(r > c) {
                r /= 2;
            } else c /= 2;

            answer++;
        }
        return answer;
    }
}