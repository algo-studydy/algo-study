class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int wMax = Integer.max(wallet[0], wallet[1]);
        int wMin = Integer.min(wallet[0], wallet[1]);

        while(wMin < Integer.min(bill[0], bill[1])
                || wMax < Integer.max(bill[0], bill[1])){
            if(bill[0] > bill[1]) bill[0] /= 2;
            else bill[1] /= 2;
            answer++;
        }
        return answer;
    }
}