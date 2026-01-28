class Solution {
    static int idx;
    static int r1, c1;
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int ans = Integer.MAX_VALUE;
        idx = 0;
        r1 = 0; c1 = 0;
        int n = sequence.length;
        int[] arr = new int[n];
        int end = 0;
        arr[0] = sequence[0];
        if(arr[0] == k) {
            answer[0] = 0;
            answer[1] = 0;
            return answer;
        }
        for(int i=1;i<n;i++) {
            if(sequence[i] <= k) arr[i] = arr[i-1] + sequence[i];
            else break;

            if(arr[i] > k) {
                while(arr[i] > k) {
                    arr[i] -= sequence[idx++];

                }
            }

            if(arr[i] == k) {
                end = i;
                if(ans > (end - idx)) {
                    ans = end - idx;
                    r1 = end;
                    c1 = idx;
                }
            }
        }

        answer[0] = c1;
        answer[1] = r1;

        return answer;
    }
}