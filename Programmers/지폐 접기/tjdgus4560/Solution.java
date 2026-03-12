class Solution {
    public int solution(int[] wallet, int[] bill) {
        int ans=0;
        int max = Math.max(wallet[0],wallet[1]);
        int min = Math.min(wallet[0],wallet[1]);

        while(Math.max(bill[0],bill[1]) > max || Math.min(bill[0],bill[1]) > min) {
            if(bill[0]>=bill[1]) bill[0]/=2;
            else bill[1]/=2;
            ans++;
        }
        return ans;
    }
}