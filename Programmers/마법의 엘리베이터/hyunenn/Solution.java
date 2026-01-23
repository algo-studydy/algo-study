class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey > 0) {
            int d = storey % 10;
            int next = (storey / 10) % 10;

            if(d < 5) {
                answer += d;
                storey /= 10;
            }
            else if(d > 5) {
                answer += (10 - d);
                storey = (storey / 10) + 1;
            }
            else {
                if(next <= 4) {
                    answer += d;
                    storey /= 10;
                } else {
                    answer += d;
                    storey = (storey / 10) + 1;
                }
            }
        }
        return answer;
    }
}